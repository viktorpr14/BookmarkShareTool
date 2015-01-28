/**
 * Created by vv on 08.01.2015.
 */

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
    };
    factory.getNotMembersByTeamId = function(teamId) {
        return $http.get('/rest/notMembers/' + teamId);
    };
    factory.inviteUserToTeam = function(teamId, userId) {
        $http.get('/rest/inviteUser/' + teamId + '/' + userId);
    };
    factory.getInvitationsByUsername = function(userName) {
        return $http.get('/rest/invitations/' + userName);
    };
    factory.acceptInvitation = function(userTeamId) {
        $http.put('/rest/acceptInvitation/' + userTeamId);
    };
    factory.rejectInvitation = function(userTeamId) {
        $http.put('/rest/rejectInvitation/' + userTeamId);
    };
    factory.getTreeOfBookmarksByTeamId = function(teamId) {
        return $http.get('/rest/team/bookmarks/' + teamId);
    };
    factory.getTreeOfBookmarksByUserId = function(userId) {
        return $http.get('/rest/user/bookmarks/' + userId);
    };

    return factory;
});
