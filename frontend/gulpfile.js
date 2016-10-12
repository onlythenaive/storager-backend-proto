(function (imports) {

  'use strict';

  // NOTE: command line arguments retrieval
  const args = imports.commandLineArgs([
    {
      name: 'port',
      type: Number
    }
  ]);

  // NOTE: default build configuration options
  const defaults = {

    port: 8090,

    paths: {

      backendMock: './backend-mock/main.js',

      staticAssets: './src/'
    }
  };

  // NOTE: backend mock startup task
  imports.gulp.task('backend-mock-deploy', function (callback) {

    var started = false;

    return imports.nodemon({
             script: defaults.paths.backendMock,
             args: [
               '--assets=' + defaults.paths.staticAssets,
               '--port=' + (isNaN(args.port) ? defaults.port : args.port)
             ]
           })
           .on('start', function () {
             if (!started) {
               callback();
               started = true; 
             } 
           });
   });
})({

  commandLineArgs: require('command-line-args'),
  gulp: require('gulp'),
  nodemon: require('gulp-nodemon')
});
