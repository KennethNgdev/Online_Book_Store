<!DOCTYPE html>
<html>
<head><title>Online Book Store</title></head>
<body>
    <h1>Welcome</h1>
    <c:url var="shoppingCartUrl" value='/user/cart' />
    <a href="${shoppingCartUrl}">shopping cart</a><br>
    <c:choose>
    <c:when test="${!pageContext.request.isUserInRole('ROLE_USER')}">
    <a href="<c:url value="/login"/>">login</a><br>
    </c:when>
    <c:otherwise>
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Logout" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    </c:otherwise>
    </c:choose>
    <h2>Books</h2>
    <c:choose>
        <c:when test="${empty booksWithImageData}">
        <p>There are no book available yet!</p>
        </c:when>
        <c:otherwise>
        <c:forEach items="${booksWithImageData}" var="bookWithImageData">
        <div  class="book-container">
            <a href="<c:url value="/book/info/${bookWithImageData.book.bookId}"/>">

                <img src="data:image/jpeg;base64,${bookWithImageData.imageData}" alt="Book Cover" class="book-cover">
            </a>

            <div class="book-details">
                <div class="book-name">
                    <p >${bookWithImageData.book.bookName}</p>
                </div>
                <div class="book-price">
                    <p >HK$${bookWithImageData.book.price}</p>
                </div>
                <div>
                    <p>availability: ${bookWithImageData.book.availability}</p>
                </div>
            </div>
        </div>
        </c:forEach>
    </c:otherwise>
    </c:choose>

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
        width: 230px;
        height: auto;
        border: 1px solid black;
    }

    .book-name {
        font-size: 22px;
        font-weight: bold;
    }

</style>
</html>
