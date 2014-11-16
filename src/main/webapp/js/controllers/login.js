'use strict';

angular.module('Login', []).controller('LoginController', function($scope, LoginService){
    $scope.initialize = function(){

    };

    $scope.callLogin = function(){
        LoginService.getLoginInformation().then(function(response){
            $scope.loginInformation = response.data;
        })

    }
}).factory('LoginService', function($http){
    return {
        getLoginInformation: function(){
            return $http.get('/login');
        }
    }
});