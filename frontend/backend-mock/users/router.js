(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  var router;

  function apply(server, routePrefix) {
    server.use(routePrefix + '/users', router);
  }

  router = imports.express.Router()

      .get('/authenticated', function (request, response) {
        const user = imports.rejectUnauthorized([], request);
        response.json(imports.service.getByLogin(user.login));
      });
})({

  express: require('express'),

  service: require('./service'),
  rejectUnauthorized: require('../utils/reject-unauthorized')
});
