'use strict';

angular.module('testapp2App')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


