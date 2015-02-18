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

        <div>
            <form action="signin/google" method="POST">
                <input type="image"  src="/resources/img/signinWithGoogle.png" alt="Submit" style="width: 200px; height: 32px"/>
                <input type="hidden" name="scope" value="email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/latitude.all.best" />
                <input type="hidden" name="request_visible_actions" value="http://schemas.google.com/AddActivity http://schemas.google.com/BuyActivity http://schemas.google.com/CheckInActivity http://schemas.google.com/CommentActivity http://schemas.google.com/CreateActivity http://schemas.google.com/DiscoverActivity http://schemas.google.com/ListenActivity http://schemas.google.com/ReserveActivity http://schemas.google.com/ReviewActivity http://schemas.google.com/WantActivity"/>
                <input type="hidden" name="access_type" value="offline"/>
            </form>
        </div>

        <div>
            <form action="signin/facebook" method="POST">
                <input type="image"  src="/resources/img/signinWithFacebook.png" alt="Submit" style="width: 200px; height: 32px"/>
                <input type="hidden" name="scope" value="email" />
            </form>
        </div>

    </body>
</html>