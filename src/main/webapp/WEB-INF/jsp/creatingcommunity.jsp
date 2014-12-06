<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Creating New Community</title>
</head>
<body>

<sf:form method="post" modelAttribute="community">
  <fieldset>
    <table cellspacing="0">

      <tr>
        <th><label for="communityName">Community name:</label></th>
        <td><sf:input path="communityName" size="20" id="communityName"/></td>
      </tr>
      <tr>
        <td colspan="3"><input type="submit"/></td>
      </tr>

    </table>
  </fieldset>
</sf:form>


</body>
</html>
