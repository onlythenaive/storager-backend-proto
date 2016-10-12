(function (imports) {

  'use strict';

  // NOTE: application module
  module.exports = {

    start: start
  };

  // NOTE: default configuration properties
  const defaults = {

    routes: {

      assets: '/static',

      data: '/data',

      security: '/security'
    }
  };

  // NOTE: application startup
  function start(config) {

    const server = imports.express();

    imports.tokenInterceptor.apply(server, defaults.routes.data);

    imports.indicatorRouter.apply(server, defaults.routes.data);
    imports.patchRouter.apply(server, defaults.routes.data);
    imports.periodRouter.apply(server, defaults.routes.data);
    imports.providerRouter.apply(server, defaults.routes.data);
    imports.territoryRouter.apply(server, defaults.routes.data);
    imports.userRouter.apply(server, defaults.routes.data);
    imports.logonRouter.apply(server, defaults.routes.security);
    imports.logoutRouter.apply(server, defaults.routes.security);

    server
        .use(defaults.routes.assets, imports.express.static(config.paths.assets))
        .get('/*', function (request, response) {
          response.sendFile(imports.path.resolve(config.paths.assets + 'index.html'));
        })
        .use(function (error, request, response, next) {
          if (error === 'NOT_AUTHORIZED') {
            response
                  .status(401)
                  .json({
                    message: "NOT_AUTHORIZED"
                  });
          } else {
            next(error);
          }
        })
        .listen (config.port);
  }
})({

  express: require('express'),
  path: require('path'),

  indicatorRouter: require ('./indicators/router'),
  patchRouter: require('./patches/router'),
  periodRouter: require('./periods/router'),
  providerRouter: require('./providers/router'),
  territoryRouter: require('./territories/router'),
  userRouter: require('./users/router'),

  logonRouter: require('./security/logon/router'),
  logoutRouter: require('./security/logout/router'),
  tokenInterceptor: require('./security/tokens/interceptor')
});
