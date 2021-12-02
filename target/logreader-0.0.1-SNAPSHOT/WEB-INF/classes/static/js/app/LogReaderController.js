'use strict'

var module = angular.module('demo.controllers', []);
module.controller("LogReaderController", ["$scope", "LogReaderService",
    function($scope, LogReaderService) {

        $scope.logInput = {
            fileName: null,
            event: null,
            event: null
        };
        LogReaderService.searchLog($scope.logInput).then(function(value) {
            $scope.logOutput = value.data;
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });

    }
]);