'use strict'

angular.module('demo.services', []).factory('LogReaderService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.searchLog = function(logInput) {
				return $http.post(CONSTANTS.searchLog, logInput);
			}
			return service;
		} ]);