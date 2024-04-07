<html>
<head>
    <title>Book Information</title>

</head>
<body>
<h2>Information</h2>
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
    <div class="add-to-cart">
        <a href="<c:url value='addCart/${bookWithImageData.book.bookId}'/>" >Add to cart</a>
    </div>
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
                    <c:choose>
                        <c:when test="${pageContext.request.isUserInRole('admin')}">
                            <div class="comment-user-name"><p>${comment.user.username} (admin)</p></div>
                        </c:when>
                        <c:otherwise>
                            <div class="comment-user-name"><p>${comment.user.username} (user)</p></div>
                        </c:otherwise>
                    </c:choose>
                    <div class="comment-user"><p>${comment.context}</p></div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <div class="comment-input">
        <div class="comment-user-name"><p>username</p></div>
        <div class="user-input">
            <div id="comment-area">
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('admin') || pageContext.request.isUserInRole('user')}">
                        <div><p>Add comment</p></div>
                        <form:form method="post" modelAttribute="newUserComment">
                            <form:textarea path="context" id="comment-input" rows="4" cols="50" placeholder="Write your comment here..."/>
                            <input type="submit" value="submit" id="submit-comment" disabled>
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <div><p>You need to login to add a comment</p></div>
                        <form:form method="post" modelAttribute="newUserComment">
                            <form:textarea path="context" id="comment-input" rows="4" cols="50" placeholder="Write your comment here..."/>
                            <input type="submit" value="submit" id="submit-comment" disabled>
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
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
