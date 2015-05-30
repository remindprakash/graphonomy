'use strict';

angular.module('testapp2App')
    .controller('DatabaseController', function ($scope, $translate, $timeout, Database) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorDatabaseExists = null;
        $scope.createDatabase = {};
        $timeout(function (){angular.element('[ng-model="createDatabase.databaseName"]').focus();});

        $scope.create = function () {
                Database.save($scope.createDatabase,function () {
                    $scope.success = 'OK';
                },function (response) {
                    $scope.success = null;
                    if (response.status === 400 && response.data === 'Database already exists') {
                        $scope.errorDatabaseExists = 'ERROR';
                    } else {
                        $scope.error = 'ERROR';
                    }
                });
            }
    });
