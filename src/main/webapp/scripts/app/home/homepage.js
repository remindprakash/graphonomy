'use strict';

angular.module('testapp2App')
    .config(function ($stateProvider) {
        $stateProvider
            .state('homepage', {
                parent: 'site',
                url: '/',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/home/homepage.html',
                        controller: 'HomepageController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('homepage');
                        return $translate.refresh();
                    }]
                }
            });
    });
