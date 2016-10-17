(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  var router;

  function apply(server, routePrefix) {
    server.use(routePrefix + '/logon', router);
  }

  router = imports.express.Router()

      .use(imports.bodyParser.json())

      .post('/', function (request, response) {
        const credentials = request.body;
        if (!credentials || !credentials.login || !credentials.secret) {
          throw "NOT_AUTHORIZED";
        }
        const user = imports.userRepository.findOne({login: credentials.login});
        if (!user || !user.enabled || user.secret !== credentials.secret) {
          throw "NOT_AUTHORIZED";
        }
        response.json(imports.tokenRepository.create(credentials.login));
      });
})({

  bodyParser: require('body-parser'),
  express: require('express'),

  tokenRepository: require('../tokens/repository'),
  userRepository: require('../../users/repository')
});
