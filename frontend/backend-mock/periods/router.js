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
        response.json(imports.repository.getAll());
      });
})({

  express: require('express'),

  repository: require('./repository'),
  rejectUnauthorized: require('../utils/reject-unauthorized')
});
