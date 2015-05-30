'use strict';

angular.module('testapp2App')
    .controller('MainController', function ($scope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            var isAuth = Principal.isAuthenticated;
            if(isAuth) {
            	$state.go('homepage');
            } 
        });
    });
