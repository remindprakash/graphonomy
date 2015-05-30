'use strict';

angular.module('testapp2App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tools', {
                abstract: true,
                parent: 'site'
            });
    });
