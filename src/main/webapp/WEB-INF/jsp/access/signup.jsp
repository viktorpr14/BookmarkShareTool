<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script type="text/javascript">

        function checkPassword(){
            if (document.getElementById("password").value===document.getElementById("repassword").value)
                document.getElementById("submit").disabled=false
            else{
                document.getElementById("repassword").title="passwords don't matches"
                document.getElementById("repassword").title.disabled=false;
                document.getElementById("submit").disabled=true;
            }
            return;
        }
    </script>
</head>
<body>
<form modelAttribute="user" method="post">
    <fieldset>
        <label for="firstName">First Name</label>
        <br>
        <input id="firstName" name="firstName" size="35" maxlength="50" type="text" required="required"
               pattern="[ A-Za-z]{2,50}" title="First name have to be between 2 and 50 letters."/>
        <br>
        <label for="lastName">Last Name</label>
        <br>
        <input id="lastName" name="lastName" size="35" maxlength="50" type="text" required="required"
               pattern="[ A-Za-z]{2,50}" title="Last name have to be between 2 and 50 letters."/>
        <br>
        <label for="username">Username</label>
        <br>
        <input id="username" name="username" size="35" maxlength="50" type="text" required="required"
               pattern="[ A-Za-z0-9._-]{2,50}" title="Last name have to be between 2 and 50 symbols. [,;:-_!@#$%^&+=] are not allowed"/>
        <br>
        <label for="email">E-mail</label>
        <br>
        <input id="email" name="email" size="35" maxlength="50" type="email" required="required"/>
        <br>
        <label for="password">Password</label>
        <br>
        <input id="password" name="password" size="18" maxlength="18" type="password" required="required"
               pattern="[ A-Za-z0-9.,;:-_!@#$%^&+=]{6,18}" title="Password have to be between 6 and 18 symbols."/>
        <br>
        <label for="repassword">Retype password</label>
        <br>
        <input id="repassword" name="repassword" size="18" maxlength="18" type="password" required="required"
               pattern="[ A-Za-z0-9.,;:-_!@#$%^&+=]{6,18}" title="Password must be between 6 and 18 symbols."
                onchange="checkPassword()"/>
        <br>
        <input type="submit" value="Sign up" id="submit" disabled="true">
    </fieldset>
</form>
</body>
</html>