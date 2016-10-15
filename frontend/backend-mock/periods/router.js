(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  var router;

  function apply(server, routePrefix) {
    server.use(routePrefix + '/periods', router);
  }

  router = imports.express.Router()

      .get('/', function (request, response) {
        imports.rejectUnauthorized(['USER', 'ADMIN'], request);
        response.json(imports.service.getAll());
      });
})({

  express: require('express'),

  service: require('./service'),
  rejectUnauthorized: require('../utils/reject-unauthorized')
});
