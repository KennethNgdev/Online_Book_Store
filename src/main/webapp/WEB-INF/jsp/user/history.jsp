<html>
<head>
    <title>
        Order History
    </title>
</head>
<body>
<H2>Order History</H2>
<form action="<c:url value="/logout"/>" method="post">
    <input type="submit" value="Logout" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form><br>
<c:url var="cart_Url" value="/user/cart"/>
<a href="${cart_Url}">back</a><br>
</body>
</html>
