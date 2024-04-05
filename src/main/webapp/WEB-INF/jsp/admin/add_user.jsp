<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Add User</title>
</head>
<body>
<h2>Add User</h2>
<form:form method="post" enctype="multipart/form-data" >
    <form:label path="userName">User Name</form:label><br/>
    <form:input path="userName" type="text"/><br/>
    <form:label path="firstName">First Name</form:label><br/>
    <form:input path="firstName" type="text"/><br/>
    <form:label path="lastName">Last Name</form:label><br/>
    <form:input path="lastName" type="text"/><br/>
    <form:label path="email">Email</form:label><br/>
    <form:input path="email" type="text"/><br/>
    <form:label path="deliveryAddress">Delivery Address</form:label><br/>
    <form:input path="deliveryAddress" type="text"/><br/>
    <form:label path="password">Password</form:label><br/>
    <form:input path="password" type="text"/><br/>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
