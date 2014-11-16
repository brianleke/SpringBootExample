'use strict';

var springAngularApp = angular.module('springAngularApp', ['ngRoute', 'Users', 'Login']);

springAngularApp.config(['$routeProvider', function($routeProvider){
    $routeProvider.when('/login', {
        templateUrl: 'templates/login.html',
        controller: 'LoginController'
    }).when('/users', {
        templateUrl: 'templates/users.html',
        controller: 'UserController'
    });
}]);


