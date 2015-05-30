'use strict';

angular.module('testapp2App')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
