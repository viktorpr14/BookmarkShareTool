<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team Profile</title>

  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
  <script>

/*
    $(document).ready( function () {
      $('#invite').click( function() {
        alert("HELLO from JQuery!")
      });
    })
*/

/*
    function sayHello () {
      alert("Hello from Javascript!")
    }
*/

    $(document).ready( function () {
      $('#invite').click( function() {
        var team_id = ${team.teamId};
        $.get('./getNotMembersForTeam?teamId='+team_id, function(responseJson) {
            var $select = $('#users');
            $select.find('option').remove();
            $('<option selected>').val("-1").text("--Please select--").appendTo($select);
            $.each(responseJson, function(key, value) {
                $('<option>').val(key).text(value).appendTo($select);
            });
        });
      });
    })


  </script>
</head>
<body>

  <table>
    <tr>
      <td>Team Name</td>
      <td>${team.teamName}</td>
    </tr>
  </table>

  <%--<a href="inviteUserToTeam/${team.teamId}">Invite User</a>--%>
  <input type="button" id="invite" name="invite" value="Invite Members" /> <%--onclick="sayHello()"/>--%>

  <select name="users" id="users">
      <option value="-1"></option>
  </select>
  <a href="logout">Logout</a>
</body>
</html>
