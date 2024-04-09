<html>
<head>
    <title>Check out</title>
</head>
<body>
<h2>Check out</h2>
<form action="<c:url value="/logout"/>" method="post">
    <input type="submit" value="Logout" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form><br>
<c:url var="listUrl" value="/book"/>
<a href="${listUrl}">back</a><br>

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
                </tr>
            </c:forEach>
        </table>
        <br>
    </c:otherwise>
</c:choose>
<p>Delivery Address: ${pageContext.request.getAttribute("address")}</p><br>
<p>Total Quantity: ${pageContext.request.getAttribute("totalBooks")}</p><br>
<p>Total Price: ${pageContext.request.getAttribute("totalPrice")}</p><br>
<p></p><br>
<a href="<c:url value="/user/payment"/>">Pay Now!</a>
</body>
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
