<html>
<head>
    <title>Shopping Cart</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>Shopping Cart</h2>
<form action="<c:url value="/logout"/>" method="post">
    <input type="submit" value="Logout" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form><br>
<c:url var="listUrl" value="/book"/>

<c:choose>
    <c:when test="${not empty param.orderId}">
        <p>You have successfully ordered!!</p>
    </c:when>
</c:choose>
<br>
<a href="<c:url value="/user/order/history"/>">Order history</a><br>
<br><a href="${listUrl}">back</a><br>

<c:choose>
    <c:when test="${fn:length(sessionScope.cart) == 0}">
        <p>There are no book add in shopping cart.</p>
    </c:when>
    <c:otherwise>
        <table>
            <tr><th>Book Name</th><th>Author</th><th>Price</th><th>Description</th><th>quantity</th></tr>
            <c:forEach items="${cart}" var="cart_item">
                <tr>
                    <td>${cart_item.value.book.bookName}</td>
                    <td>${cart_item.value.book.author}</td>
                    <td>${cart_item.value.book.price}</td>
                    <td class="description">${cart_item.value.book.description}</td>
                    <td>${cart_item.value.quantity}</td>
                    <td><a href="<c:url value="/user/cart/delete/${cart_item.key}"/>" >delete</a></td>
                    <td>
                        <div class="remove-from-cart">
                            <select id="quantitySelect" name="quantity" onchange="updateAddToCartUrl()">
                                <c:forEach begin="1" end="10" var="i">
                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                            <a id="removeFromCartLink" href="/app/book/removeFromCart/${cart_item.value.book.bookId}?quantity=1" onclick="removeFromCart()">Remove from Cart</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><br>

        <a href="<c:url value="checkout"/>" >checkout</a>
    </c:otherwise>
</c:choose>
</body>
<script>
    function updateAddToCartUrl() {
        var selectElement = document.getElementById("quantitySelect");
        var quantity = selectElement.value;
        var addToCartLink = document.getElementById("removeFromCartLink");
        var currentUrl = addToCartLink.getAttribute("href");
        var updatedUrl = currentUrl.split("?")[0] + "?quantity=" + quantity;
        addToCartLink.setAttribute("href", updatedUrl);
    }

    function removeFromCart() {
        var addToCartLink = document.getElementById("removeFromCartLink");
        var updatedUrl = addToCartLink.getAttribute("href");
        window.location.href = updatedUrl;
    }
</script>
<style>
    table{
        border-collapse: collapse;
    }
    table th, td{
        border: 1px solid black;
    }
    .description{
        width: 200px;
    }
</style>
</html>
