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
        const clientCredential = request.body;
        if (!clientCredential || !clientCredential.login || !clientCredential.secret) {
          throw "NOT_AUTHORIZED";
        }
        const login = clientCredential.login;
        const secret = clientCredential.secret;
        const credential = imports.credentialRepository.findByUserLogin(login);
        if (!credential || credential.login !== login || credential.secret !== secret) {
          throw "NOT_AUTHORIZED";
        }
        const user = imports.userRepository.findByLogin(login);
        if (!user.enabled) {
          throw "NOT_AUTHORIZED";
        }
        response.json(imports.tokenRepository.create(login));
      });
})({

  bodyParser: require('body-parser'),
  express: require('express'),

  credentialRepository: require('../credentials/repository'),
  tokenRepository: require('../tokens/repository'),
  userRepository: require('../../users/repository')
});
