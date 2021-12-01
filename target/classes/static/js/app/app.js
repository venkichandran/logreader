'use strict'

var demoApp = angular.module('logreader', ['ui.bootstrap']);

//demoApp.use("/static", express.static('./static/'));

demoApp.constant("CONSTANTS", {
    searchLog: "/logstreamer"
});