/**
 * Created by vv on 18.01.2015.
 */

/*
app.directive('collection', function() {
    return {
        restrict: "E",
        replace: true,
        scope: {
            collection: '='
        },
        template: "<ul><member ng-repeat='member in collection' member='member'></member></ul>"
    }
});
*/

/*
app.directive('member', function($compile) {
    return {
        restrict: "E",
        replaced: true,
        scope: {
            member: '='
        },
        template: "<li class='folderName' style='list-style: none'>{{member.folderName}}</li>",
        link: function(scope, element, attrs) {
            if (angular.isArray(scope.member.listOfTreeNodes)) {
                element.append("<collection collection='member.listOfTreeNodes'></collection>" +
                    "<ul ng-repeat='bookmark in member.listOfBookmarks' style='list-style: none'>" +
                        "<li>" +
                            "<a ng-if='bookmark.name'  class='bookmarkName' href='{{bookmark.URL}}' target='_blank'>{{bookmark.name}}</a>" +
                            "<a ng-if='!bookmark.name' class='bookmarkName' href='{{bookmark.URL}}' target='_blank'>{{bookmark.URL}} </a>" +
                        "</li>" +
                    "</ul>");
                $compile(element.contents())(scope)
            }
        }
    }
});*/

app.directive('collection', function() {
    return {
        restrict: "E",
        replace: true,
        scope: {
            collection: '='
        },
        template: "<member ng-repeat='member in collection' member='member'></member>"
    }
});

app.directive('member', function($compile) {
    return {
        restrict: "E",
        replaced: true,
        scope: {
            member: '='
        },
        template: "",

        link: function(scope, element, attrs) {

            var pre = "<li class='closedFolder' style='list-style: none' ng-init='member.isHidden=false'>" +
                         "<a href ng-click='member.isHidden=!member.isHidden' style='text-decoration: none; color: black'>{{member.folderName}}</a>" +
                      "</li>";

            var nestedFolders = "<ul ng-hide='member.isHidden'><collection collection='member.listOfTreeNodes'></collection></ul>";

            var nestedBookmarks =
                "<ul ng-hide='member.isHidden'>" +
                    "<li class='bookmark' ng-repeat='bookmark in member.listOfBookmarks' style='list-style: none'>" +
                        "<a ng-if='bookmark.name'  href='{{bookmark.URL}}' target='_blank'>{{bookmark.name}}</a>" +
                        "<a ng-if='!bookmark.name' href='{{bookmark.URL}}' target='_blank'>{{bookmark.URL}} </a>" +
                    "</li>" +
                "</ul>";

            var result;

            if (angular.isArray(scope.member.listOfTreeNodes)) {
                element.append(pre + nestedFolders + nestedBookmarks);
            } else {
                element.append(pre + nestedBookmarks);
            }
            $compile(element.contents())(scope)

        }
    }
});
