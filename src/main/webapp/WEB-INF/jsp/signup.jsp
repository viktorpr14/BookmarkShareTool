<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<sf:form method="POST" modelAttribute="user">
<fieldset>
    <table cellspacing="0">
        <tr>
            <th><label for="firstName">First name:</label></th>
            <td><sf:input path="firstName" size="20" id="firstName"/></td>
        </tr>
        <tr>
            <th><label for="lastName">Last name:</label></th>
            <td><sf:input path="lastName" size="20" id="lastName"/></td>
        </tr>
        <tr>
            <th><label for="username">Username:</label></th>
            <td><sf:input path="username" size="15" maxlength="15" id="username"/>
                <small id="username_msg">No spaces, please.</small>
            </td>
        </tr>
        <tr>
            <th><label for="email">EmailAddress:</label></th>
            <td><sf:input path="email" size="30" id="email"/>
                <small>Your email</small>
            </td>
        </tr>
        <tr>
            <th><label for="password">Password:</label></th>
            <td><sf:password path="password" size="30" showPassword="true" id="password"/>
                <small>6 characters or more</small>
            </td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit"/></td>
        </tr>
    </table>
</fieldset>
</sf:form>

</html>