(function (imports) {

  'use strict';

  // NOTE: command line arguments retrieval
  const args = imports.commandLineArgs([
    {
      name: 'assets',
      type: String
    },
    {
      name: 'port',
      type: Number
    }
  ]);

  // NOTE: application startup
  imports.application.start({

    paths: {

      assets: args.assets
    },

    port: args.port
  });
})({

  commandLineArgs: require('command-line-args'),

  application: require('./application')
});
