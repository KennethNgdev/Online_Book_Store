<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h2>Add User</h2>
<form:form method="post" enctype="multipart/form-data" modelAttribute="userForm">
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
    <form:label path="role">User</form:label>
    <form:radiobutton path="role" itemValue="user" itemLabel="User"/><br/>
    <form:label path="role">Admin</form:label>
    <form:radiobutton path="role" itemValue="admin" itemLabel="Admin"/><br/>
    <form:label path="password" >Password</form:label><br/>
    <form:password path="password" id="password1" onchange="validatePassword()"/><br/>
    <form:label path="confirmPassword">Confirm Password</form:label><br/>
    <form:password path="confirmPassword" id="password2" onchange="checkSamePassword()"/><br/>
    <p id="hint" style="display:none;">password are not same</p><br/>
    <input type="submit" value="Submit" id="submit" style="display: none"/>
</form:form>
<a href="<c:url value="/admin/"/>">back</a>
</body>
<script>
    function validatePassword() {
        if (document.getElementById("password1").value.length !== 0 ) {
            checkSamePassword();
        }
    }
    function checkSamePassword() {
        if (document.getElementById("password2").value.length !== 0) {
            if(document.getElementById("password1").value === document.getElementById("password2").value){
                document.getElementById("hint").style.display = "none";
                document.getElementById("submit").style.display = "block";
            }else{
                document.getElementById("hint").style.display = "block";
                document.getElementById("submit").style.display = "none";
            }
        }
    }
</script>
</html>
