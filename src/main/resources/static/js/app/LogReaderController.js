'use strict'

var module = angular.module('demo.controllers', ['ngRoute']);
module.controller("LogReaderController", ["$scope", "LogReaderController",
    function($scope, LogReaderController) {

        $scope.logInput = {
            fileName: null,
            event: null,
            event: null
        };
        LogReaderController.searchLog($scope.logInput).then(function(value) {
            $scope.logOutput = value.data;
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });

    }
]);