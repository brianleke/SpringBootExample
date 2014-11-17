'use strict';

angular.module('Users', []).controller('UserController', function($scope, UserService){

    $scope.initialize = function(){
        UserService.getUserInformation().then(function(response){
            $scope.user = response.data;
        });
    };

}).factory('UserService', function($http){
    return {
        getUserInformation: function(){
            return $http.get('/users');
        }
    }
});