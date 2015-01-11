/**
 * Created by vv on 08.01.2015.
 */

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
        })
        .when('/invitations/:username',
        {
            controller: 'InvitationsController',
            templateUrl: '/resources/partials/invitations.html'
        });
});

