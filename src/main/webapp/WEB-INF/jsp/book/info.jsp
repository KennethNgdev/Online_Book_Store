<html>
<head>
    <title>Book Information</title>
</head>
<body>
<h2>Information</h2>
<c:url var="shoppingCartUrl" value='/user/cart' />
<a href="${shoppingCartUrl}"/>shopping cart</a><br>
<c:choose>
    <c:when test="${!pageContext.request.isUserInRole('ROLE_ADMIN') || !pageContext.request.isUserInRole('ROLE_USER')}">
        <a href="<c:url value="/login"/>">login</a><br><br>
    </c:when>
    <c:otherwise>
        <form action="<c:url value="/logout"/>" method="post">
            <input type="submit" value="Logout" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:otherwise>
</c:choose>
<c:url var="listUrl" value="/book"/>
<a href="${listUrl}">back</a>
<div  class="book-container">
    <div class="book-name">
        <p >${bookWithImageData.book.bookName}</p>
    </div>
    <img src="data:image/jpeg;base64,${bookWithImageData.imageData}" alt="Book Cover" class="book-cover">
    <div class="author">
        <span>${bookWithImageData.book.author}</span>
    </div>
    <div class="description">
        <p>${bookWithImageData.book.description}</p>
    </div>
    <div class="book-price">
        <p>HK$${bookWithImageData.book.price}</p>
    </div>
    <div>
        <p>availability: ${bookWithImageData.book.availability}</p>
    </div>

    <c:choose>
    <c:when test="${bookWithImageData.book.availability eq 'true'}">
        <div class="add-to-cart">
            <select id="quantitySelect" name="quantity" onchange="updateAddToCartUrl()">
                <c:forEach begin="1" end="10" var="i">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <a id="addToCartLink" href="/app/book/addToCart/${bookId}?quantity=1" onclick="addToCart()">Add to Cart</a>
        </div>
    </c:when>
    </c:choose>
</div>
<div class="comment-container">
    <div class="comment-header">
        <h2>Comments</h2>
    </div>

    <c:choose>
        <c:when test="${empty bookWithImageData.book.comments}">
            There are no comments!
        </c:when>
        <c:otherwise>
            <c:forEach items="${bookWithImageData.book.comments}" var="comment">
                <div class="comment">

                    <div class="comment-user-name"><p>${comment.user.username} </p></div>

                    <div class="comment-user"><p>${comment.context}</p></div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <div class="comment-input">
        <c:choose>
            <c:when test="${empty pageContext.request.remoteUser}">
                <div class="comment-user-name"><p>username</p></div>
            </c:when>
            <c:otherwise>
                <div class="comment-user-name"><p>${pageContext.request.remoteUser}</p></div>
            </c:otherwise>
        </c:choose>

        <div class="user-input">
            <div id="comment-area">
                <c:choose>
                    <c:when test="${not empty pageContext.request.remoteUser}">
                        <div><p>Add comment</p></div>
                        <form:form method="post" modelAttribute="newUserComment">
                            <form:input type="text" path="username" hidden="hidden" value="${pageContext.request.remoteUser}"/>
                            <form:textarea path="context" id="comment-input" rows="4" cols="50" placeholder="Write your comment here..."/>
                            <input type="submit" value="submit" id="submit-comment">
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <div><p>You need to login to add a comment</p></div>
                        <form:form method="post" modelAttribute="newUserComment">
                            <form:input type="text" path="username" hidden="hidden" value="${pageContext.request.remoteUser}"/>
                            <form:textarea path="context" id="comment-input" rows="4" cols="50" placeholder="Write your comment here..."/>
                            <input type="submit" value="submit" id="submit-comment" disabled >
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function updateAddToCartUrl() {
        var selectElement = document.getElementById("quantitySelect");
        var quantity = selectElement.value;
        var addToCartLink = document.getElementById("addToCartLink");
        var currentUrl = addToCartLink.getAttribute("href");
        var updatedUrl = currentUrl.split("?")[0] + "?quantity=" + quantity;
        addToCartLink.setAttribute("href", updatedUrl);
    }

    function addToCart() {
        var addToCartLink = document.getElementById("addToCartLink");
        var updatedUrl = addToCartLink.getAttribute("href");
        window.location.href = updatedUrl;
    }
</script>
<style>
    .book-cover {
        margin-top: 10px;
        width: 200px;
        height: 300px;
    }
    .book-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        margin-bottom: 20px;
        margin-top: 20px;
        width: 230px;
        height: auto;
        border: 1px solid black;
    }

    .book-name {
        font-size: 22px;
        font-weight: bold;
    }

    .author{
        margin-top: 5px;
    }
    .author::before{
        content: "Author: ";
        font-weight: bold;
    }

    .add-to-cart {
        display: inline-block;
        padding: 5px 5px;
        background-color: #7cd2ff;
        color: #fff;
        text-decoration: none;
        transition: background-color 0.3s;
        margin-bottom: 10px;
    }
    .add-to-cart:hover {
        background-color: #3498db;
    }
    .comment-container {
        margin-bottom: 20px;
        width: 500px;
        height: auto;
        border: 1px solid black;
    }

    .comment-header h2{
        font-size: 22px;
        font-weight: bold;
        text-align: center;
    }

    .comment, .comment-input {
        display: flex;
        flex-direction: row;
        margin: 10px;
    }

    .comment-user-name {
        padding-right: 5px;
        border: 1px solid black;
    }

    .comment-user{
        padding: 0px 2px 0px 2px;
        border-right: 1px solid black;
        border-top: 1px solid black;
        border-bottom: 1px solid black;
    }
    .user-input{
        padding: 0px 2px 0px 2px;
        border-right: 1px solid black;
        border-top: 1px solid black;
        border-bottom: 1px solid black;
    }
</style>
</html>
