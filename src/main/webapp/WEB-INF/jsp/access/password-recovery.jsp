<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<body>
<div class="container">
    <p class="message">${message}</p>
    <form action="/bookmarks/password-recovery" method="post" >
        <fieldset>
            <h2>Password recovery</h2>
            <input id="email" name="email" size="20" type="text" class="input-block-level" placeholder="Type email">
            <button type="submit">Submit</button>
        </fieldset>
    </form>
</div>
</body>
</html>