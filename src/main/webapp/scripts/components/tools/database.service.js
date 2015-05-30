'use strict';

angular.module('testapp2App')
    .factory('Database', function ($resource) {
        return $resource('api/graph/database', {}, {
                'save': {method: 'POST'},
                'post': {
                    method: 'POST',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
            });
        });
