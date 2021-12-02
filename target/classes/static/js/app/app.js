'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers',
                             		'demo.services' ]);

//demoApp.use("/static", express.static('./static/'));

demoApp.constant("CONSTANTS", {
    searchLog: "/logreader"
});