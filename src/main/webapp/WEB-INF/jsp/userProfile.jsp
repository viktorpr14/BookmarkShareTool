<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="userApp">

<head>
    <link href="/resources/styles/animation.css" rel="stylesheet"/>
    <link href="/resources/styles/navbar.css" rel="stylesheet"/>

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

    <!-- Navigation links -->
    <div id="navbar">
        <span class="pos_fixed"><h3>username: ${user.username}</h3></span>
        <a href="#/" class="navbar-brand">Bookmarks Share Tool</a>
        <br/><br/>
        <a href="#/" class="navigate">Home</a>
        <a href="#userProfile/${user.username}" class="navigate">User Profile</a>
        <a href="#/teams/${user.username}" class="navigate">Show Teams</a>
        <a href="#/newTeam/${user.username}" class="navigate">Create New Team</a>
    </div>
<%--
<h2>User Profile: ${user.username}</h2>
<br/>
--%>
<%--
<a href="creatingTeam">Create Team</a>
<a href="logout">Logout</a>

--%>

<%--    <a href="#/teams/${user.username}">Show Teams</a>--%>
    <br/>

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
            })
            .when('/userProfile/:username',
            {
                controller: 'UserProfileController',
                templateUrl: '/resources/partials/userProfile.html'
            })
            .when('/newTeam/:username',
            {
                controller: 'NewTeamController',
                templateUrl: '/resources/partials/newTeam.html'
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


        app.controller('TeamProfileController', function($window, $scope, $routeParams, teamFactory) {
            init();

            function init() {
                teamFactory.getTeamById($routeParams.teamId).success(function(data) {
                    $scope.team = data;
                });
            }

            $scope.getNotMembersByTeamId = function(teamId) {
                teamFactory.getNotMembersByTeamId(teamId).success(function(data) {
                    $scope.users = data;});
            }

//            $scope.getNotMembersByTeamId = function() {
//                $window.alert('HELLOOO!');
//            }

        });


        app.controller('UserProfileController', function($scope, $routeParams, teamFactory) {
            init();

            function init() {
                teamFactory.getUserProfileByUsername($routeParams.username).success(function(data) {
                    $scope.user = data;
                });
            }

        });


        app.controller('NewTeamController', function($scope, $routeParams, $http, $location) {
            $scope.createNewTeam = function() {
                var newTeam = {
                    teamName: $scope.teamName
                }
                $http.post('/rest/createTeam/' + $routeParams.username, newTeam)
                        .success(function(data) {
                            $scope.createdTeamId = data;
                            $location.url("/team/" + $scope.createdTeamId);
                        });
                $scope.teamName = '';
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
            factory.getUserProfileByUsername = function(userName) {
                return $http.get('/rest/userProfile/' + userName);
            }
            factory.getNotMembersByTeamId = function(teamId) {
                return $http.get('/rest/notMembers/' + teamId);
            }
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