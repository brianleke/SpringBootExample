module.exports = function (config) {
    'use strict';

    config.set({
        autoWatch: true,

        basePath: '../',

        frameworks: ['jasmine'],

        files: [
            'bower_components/angular/angular.min.js',
            'bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js',
            'bower_components/angular-mocks/angular-mocks.js',
            'bower_components/angular-route/angular-route.min.js',
            './js/**/*.js',
            'tests/spec/**/*.js'
        ],

        exclude: [
        ],

        port: 8080,

        browsers: [
            'PhantomJS'
        ],

        plugins: [
            'karma-phantomjs-launcher',
            'karma-jasmine'
        ],

        singleRun: false,

        colors: true,

        logLevel: config.LOG_INFO
    });
};

