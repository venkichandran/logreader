'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers',
                             		'demo.services', 'ngRoute' ]);

//demoApp.use("/static", express.static('./static/'));

demoApp.constant("CONSTANTS", {
    searchLog: "/logreader"
});