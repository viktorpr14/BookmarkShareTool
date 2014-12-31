<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="userApp">

<head>
    <link href="/resources/styles/animation.css" rel="stylesheet"/>

    <script src="/resources/js/angular.js"></script>
    <script src="/resources/js/angular-route.js"></script>
    <script src="/resources/js/angular-animation.js"></script>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>

<%--
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
--%>
</head>

<body>

<h2>User Profile: ${user.username}</h2>
<br/>
<%--
<a href="creatingTeam">Create Team</a>
<a href="logout">Logout</a>

--%>

    <a href="#/teams/${user.username}">Show Teams</a>

    <div ng-view class="slide-animation"></div>

    <script>
        var app = angular.module('userApp', ['ngRoute', 'ngAnimate']);


        app.config(function($routeProvider) {
            $routeProvider
            .when('/teams/:username',
            {
                controller: 'TeamsController',
                templateUrl: '/resources/partials/listOfTeams.html'
            })
            .when('/team/:teamId',
            {
                controller: 'TeamProfileController',
                templateUrl: '/resources/partials/teamProfile.html'
            });
         });


        app.controller('TeamsController', function($scope, $routeParams, teamFactory) {
            init();

            function init() {
                teamFactory.getTeams($routeParams.username).success(function(data) {
                   $scope.teams = data;
                });
            }
        });


        app.controller('TeamProfileController', function($scope, $routeParams, teamFactory) {
            init();

            function init() {
                teamFactory.getTeamById($routeParams.teamId).success(function(data) {
                    $scope.team = data;
                });
            }

        });


        app.factory('teamFactory', function($http) {
            var factory = {};
            factory.getTeams = function(userName) {
                return $http.get('/rest/teams/' + userName);
            };
            factory.getTeamById = function(teamId) {
                return $http.get('/rest/team/' + teamId);
            };
            return factory;
        });

    </script>

<%--
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
--%>

</body>
</html>