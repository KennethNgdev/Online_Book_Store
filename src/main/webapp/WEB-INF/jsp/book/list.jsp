<!DOCTYPE html>
<html>
<head><title>Online Book Store</title></head>
<body>
    <h1>Welcome user</h1>
    <h2>Books</h2>
    <c:choose>
        <c:when test="${empty booksWithImageData}">
        <p>There are no book available yet!</p>
        </c:when>
        <c:otherwise>
        <c:forEach items="${booksWithImageData}" var="bookWithImageData">
        <c:choose>
        <c:when test="${bookWithImageData.book.availability eq 'true'}">
        <div  class="book-container">
            <a href="<c:url value="book/info/${bookWithImageData.book.bookId}"/>">

                <img src="data:image/jpeg;base64,${bookWithImageData.imageData}" alt="Book Cover" class="book-cover">
            </a>

            <div class="book-details">
                <div class="book-name">
                    <p >${bookWithImageData.book.bookName}</p>
                </div>
                <div class="book-price">
                    <p >HK$${bookWithImageData.book.price}</p>
                </div>
                <div class="add-to-cart">
                    <a href="<c:url value='addCart/${bookWithImageData.book.bookId}'/>" >Add to cart</a>
                </div>
            </div>
        </div>
        </c:when>
        </c:choose>
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
</style>
</html>
