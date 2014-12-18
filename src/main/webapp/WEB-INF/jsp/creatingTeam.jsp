<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Creating New Team</title>
</head>
<body>

<sf:form method="post" modelAttribute="team">
  <fieldset>
    <table cellspacing="0">

      <tr>
        <th><label for="teamName">Team name:</label></th>
        <td><sf:input path="teamName" size="20" id="teamName"/></td>
      </tr>
      <tr>
        <td colspan="3"><input type="submit"/></td>
      </tr>

    </table>
  </fieldset>
</sf:form>

<a href="logout">Logout</a>
</body>
</html>
