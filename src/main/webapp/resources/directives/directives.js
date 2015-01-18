/**
 * Created by vv on 18.01.2015.
 */

app.directive('collection', function() {
    return {
        restrict: "E",
        replace: true,
        scope: {
            collection: '='
        },
        template: "<ul><member ng-repeat='member in collection' member='member'>{{ member.folderName }}</member></ul>"
    }
});

app.directive('member', function($compile) {
    return {
        restrict: "E",
        replaced: true,
        scope: {
            member: '='
        },
        template: "<li>{{member.folderName}}</li>",
        link: function(scope, element, attrs) {
            if (angular.isArray(scope.member.listOfTreeNodes)) {
                element.append("<collection collection='member.listOfTreeNodes'></collection>");
                $compile(element.contents())(scope)
            }
        }
    }
});