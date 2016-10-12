(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  var router;

  function apply(server, routePrefix) {
    server.use(routePrefix + '/security/tokens', router);
  }

  router = imports.express.Router()

      .get('/', function (request, response) {
        imports.rejectUnauthorized(['ROOT'], request);
        response.json(imports.repository.findAll());
      });
})({

  express: require('express'),

  repository: require('./repository'),
  rejectUnauthorized: require('../../utils/reject-unauthorized')
});
