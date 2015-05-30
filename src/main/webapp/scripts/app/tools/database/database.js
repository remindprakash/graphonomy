'use strict';

angular.module('testapp2App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('createDatabase', {
                parent: 'tools',
                url: '/create-database',
                data: {
                    roles: [],
                    pageTitle: 'database.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/tools/database/database.html',
                        controller: 'DatabaseController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('createDatabase');
                        return $translate.refresh();
                    }]
                }
            });
    });
