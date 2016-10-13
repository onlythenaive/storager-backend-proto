(function (imports) {

  'use strict';

  // NOTE: time period router
  module.exports = {

    apply: apply
  };

  var router;

  // NOTE: applies the router to specified server
  function apply(server, routePrefix) {
    server.use(routePrefix + '/periods', router);
  }

  router = imports.express.Router()

      // NOTE: returns all available periods
      .get('/', function (request, response) {
        imports.rejectUnauthorized(['USER', 'ADMIN'], request);
        response.json(imports.service.getAll());
      });
})({

  express: require('express'),

  service: require('./service'),
  rejectUnauthorized: require('../utils/reject-unauthorized')
});
