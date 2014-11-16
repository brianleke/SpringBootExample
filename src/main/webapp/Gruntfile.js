'use strict';

module.exports = function (grunt) {

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Configurable paths for the application
    var appConfig = {
        app: require('./bower.json').appPath || 'app',
        dist: 'dist'
    };

    // Define the configuration for all the tasks
    grunt.initConfig({

        // The actual grunt server settings
        connect: {
            options: {
                port: 9000,
                hostname: 'localhost',
                livereload: 35729
            },
            livereload: {
                options: {
                    open: true,
                    middleware: function (connect) {
                        return [
                            connect.static('.tmp'),
                            connect().use(
                                '/bower_components',
                                connect.static('./bower_components')
                            ),
                            connect.static(appConfig.app)
                        ];
                    }
                }
            },
            test: {
                options: {
                    port: 9001,
                    middleware: function (connect) {
                        return [
                            connect.static('.tmp'),
                            connect.static('/test'),
                            connect().use(
                                '/bower_components',
                                connect.static('./bower_components')
                            ),
                            connect.static(require('./bower.json').appPath || 'app')
                        ];
                    }
                }
            },
            dist: {
                options: {
                    open: true,
                    base: 'dist'
                }
            }
        },

        watch: {
            javascript: {
                files: ['./js/**/*.js'],
                tasks: ['jshint', 'build']
            }
        },

        watchify: {
            options: {
                keepalive: true
            },
            example: {
                src: ['./js/**/*.js'],
                dest: 'dist/app.min.js'
            }
        },
        uglify: {
            all: {
                files: {
                    'dist/app.min.js': ['./js/**/*.js']
                },
                options: {
                    mangle: false
                }
            }
        },

        // Make sure code styles are up to par and there are no obvious mistakes
        jshint: {
            options: {
                jshintrc: '.jshintrc',
                reporter: require('jshint-stylish')
            },
            all: {
                src: [
                    'Gruntfile.js',
                    './js/{,*/}*.js'
                ]
            },
            test: {
                options: {
                    jshintrc: './.jshintrc'
                },
                src: [
                    './tests/spec/{,*/}*.js'
                ]
            }
        },

        // Empties folders to start fresh
        clean: {
            dist: {
                files: [
                    {
                        dot: true,
                        src: [
                            '.tmp',
                            'dist/{,*/}*',
                            '!dist/.git*'
                        ]
                    }
                ]
            },
            server: '.tmp'
        },

        // Test settings
        karma: {
            unit: {
                configFile: './tests/karma.conf.js',
                singleRun: true
            }
        },

        protractor: {
            headless: {
                options: {
                    configFile: './tests/functional_conf.js',
                    keepAlive: false,
                    args: {
                        specs: ['./tests/functional/*-spec.js'],
                        browser: 'chrome'
                    }
                }
            },
            firefox: {
                options: {
                    configFile: './tests/functional_conf.js',
                    keepAlive: false,
                    args: {
                        specs: ['./tests/functional/*-spec.js'],
                        browser: 'firefox'
                    }
                }
            }
        },

        run: {
            options: {
                wait: false
            }
        }
    });

    grunt.registerTask('serve', 'Compile then start a connect web server', function (target) {
        if (target === 'dist') {
            return grunt.task.run(['build', 'connect:dist:keepalive']);
        }

        grunt.task.run([
            'clean:server',
            'concurrent:server',
            'connect:livereload',
            'watch'
        ]);
    });

    grunt.registerTask('server', 'DEPRECATED TASK. Use the "serve" task instead', function (target) {
        grunt.log.warn('The `server` task has been deprecated. Use `grunt serve` to start a server.');
        grunt.task.run(['serve:' + target]);
    });

    grunt.registerTask('unit', [
        'clean:server',
        'connect:test',
        'karma'
    ]);

    grunt.registerTask('watch', [
        'watchify:example'
    ]);

    grunt.registerTask('build', [
        'clean:dist',
        'newer:uglify:all',
        'less'
    ]);

    grunt.registerTask('build-staging', [
        'clean:dist',
        'newer:uglify:all',
        'less'
    ]);


    grunt.registerTask('functional', [
        'build',
        'clean:server',
        'protractor:headless'
    ]);

    grunt.registerTask('functional-staging', [
        'build-staging',
        'clean:server',
        'protractor:headless'
    ]);


    grunt.registerTask('default', [
        'unit',
        'build'
    ]);
};

