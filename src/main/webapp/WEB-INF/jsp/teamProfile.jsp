<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team Profile</title>

  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
  <script>

    $(document).ready( function () {
      $('#invite').click( function() {
        var team_id = ${team.teamId};
        $.get('./getNotMembersForTeam?teamId='+team_id, function(responseJson) {
            $('#submitInvitation').toggle();
            var $select = $('#users');
            $select.toggle();
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
      <td><b>${team.teamName}</b></td>
    </tr>
  </table>

  <input type="button" id="invite" name="invite" value="Invite Members" />
  <br/>
  <form name="invitationform" id="invitationform" action="inviteUser" method="post">

    <select style="display: none" name="users" id="users">
        <option value="-1"></option>
    </select>

    <input type="hidden" id="teamId" name="teamId" value="${team.teamId}"/>
    <input style="display: none" type="submit" id="submitInvitation" value="Invite"/>

  </form>

</body>
</html>
