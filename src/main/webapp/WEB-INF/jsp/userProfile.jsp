<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="userApp">

<head>
    <link href="/resources/styles/animation.css" rel="stylesheet"/>
    <link href="/resources/styles/navbar.css" rel="stylesheet"/>
    <link href="/resources/styles/angular.treeview.css"rel="stylesheet" >

    <!-- Vendor libs  -->
    <script src="/resources/js/angular.js"></script>
    <script src="/resources/js/angular-route.js"></script>
    <script src="/resources/js/angular-animation.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>

</head>

<body>

    <!-- Navigation links -->
    <div id="navbar">
        <div><span style="float: right"><h3>username: ${user.username}</h3></span></div>
        <a href="#/" class="navbar-brand">Bookmarks Share Tool</a>
        <br/><br/>
        <a href="#/" class="navigate">Home</a>
        <a href="#userProfile/${user.username}" class="navigate">User Profile</a>
        <a href="#/teams/${user.username}" class="navigate">Show Teams</a>
        <a href="#/invitations/${user.username}" class="navigate">Show Invitations</a>
        <a href="#/newTeam/${user.username}" class="navigate">Create New Team</a>
        <a href="#/bookmark/" class="navigate">Create New Bookmark</a>
    </div>

    <br/>

    <div ng-view class="slide-animation"></div>

    <!-- App libs -->
    <script src="/resources/app.js"></script>
    <script src="/resources/controllers/controllers.js"></script>
    <script src="/resources/directives/directives.js"></script>
    <script src="/resources/services/factory.js"></script>

</body>
</html>