<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <script>

        $(document).ready( function () {
            $('#showProfile').click( function() {
                $('#profileTable').show();
                var username = '${user.username}';
                $.get('./rest/userProfile/'+username, function(responseJson) {
                    $('#firstName').text(responseJson.firstName)
                    $('#lastName').text(responseJson.lastName)
                    $('#userName').text(responseJson.username)
                    $('#email').text(responseJson.email)
                });
            });
        })

    </script>
</head>

<body>

<h2>User Profile: ${user.username}</h2>
<br/>
<a href="creatingTeam">Create Team</a>
<a href="logout">Logout</a>

<input type="button" id="showProfile" name="showProfile" value="Show Profile" />
<table style="display: none" name="profileTable" id="profileTable">
    <tr>
        <td>First Name</td>
        <td id="firstName"></td>
    </tr>
    <tr>
        <td>Last Name</td>
        <td id="lastName"></td>
    </tr>
    <tr>
        <td>Username</td>
        <td id="userName"></td>
    </tr>
    <tr>
        <td>Email</td>
        <td id="email"></td>
    </tr>
</table>

</body>
</html>