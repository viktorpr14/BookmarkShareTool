/**
 * Created by vv on 08.01.2015.
 */

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
        $('#invite').show();
        teamFactory.getNotMembersByTeamId(teamId).success(function(data) {
            $scope.users = data;
        });
    };

    $scope.inviteUserToTeam = function(teamId, userId) {
        var buttonId = '#' + userId;
        $(buttonId).attr('disabled', 'disabled');
        teamFactory.inviteUserToTeam(teamId, userId);
    };

    $scope.getBookmarksByTeamId = function(teamId) {
        $('#showBookmarks').show();
        teamFactory.getBookmarksByTeamId(teamId).success(function(data) {
            $scope.bookmarks = data;
        });
    }

});


app.controller('UserProfileController', function($scope, $routeParams, teamFactory) {
    init();

    function init() {
        teamFactory.getUserProfileByUsername($routeParams.username).success(function(data) {
            $scope.user = data;
        });
    }

    $scope.getBookmarksByUserId = function(userId) {
        $('#showBookmarks').show();
        teamFactory.getBookmarksByUserId(userId).success(function(data) {
            $scope.bookmarks = data;
        });
    };

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


app.controller('InvitationsController', function($scope, $routeParams, teamFactory) {
    init();

    function init() {
        teamFactory.getInvitationsByUsername($routeParams.username).success(function(data) {
            $scope.invitations = data;
        });
    }

    $scope.acceptInvitation = function(userTeamId) {
        //var buttonClass = '.' + userTeamId;
        //$(buttonClass).attr('disabled', 'disabled');
        $scope.invitations[userTeamId].disabled=true;
        teamFactory.acceptInvitation(userTeamId);
    }

    $scope.rejectInvitation = function(userTeamId) {
        //var buttonClass = '.' + userTeamId;
        //$(buttonClass).attr('disabled', 'disabled');
        $scope.invitations[userTeamId].disabled=true;
        teamFactory.rejectInvitation(userTeamId);
    }

});

app.controller('NewBookmarkController', function($scope, $http) {
    $scope.createNewBookmark = function() {

        $http.post('/rest/bookmark/', $scope.bookmark)
        }

});

