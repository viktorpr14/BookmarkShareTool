<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
</head>
    <body>
        <div class="container">
            <p class="message">${message}</p>
            <form action="j_spring_security_check" method="post" >
                <fieldset>
                    <h2>Login</h2>
                    <input id="j_username" name="j_username" size="20" type="text" class="input-block-level" placeholder="Username">
                    <input id="j_password" name="j_password" size="20" type="password" class="input-block-level" placeholder="Password">
                    <input id="remember_me" name="_spring_security_remember_me" type="checkbox" />
                    <label for="remember_me" class="inline">Remember me</label>
                    <button type="submit">Login</button>
                    <%--TODO perform password recovery--%>
                    <%--<p><a href="/password-recovery">Forget password?</a></p>--%>
                </fieldset>
            </form>
        </div>
    </body>
</html>