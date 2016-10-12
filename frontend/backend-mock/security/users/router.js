(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  var router;

  function apply(server, routePrefix) {
    server.use(routePrefix + '/security/users', router);
  }

  router = imports.express.Router()

      .get('/', function (request, response) {
        imports.rejectUnauthorized(['ROOT'], request);
        response.json(imports.repository.findAll());
      })

      .get('/current', function (request, response) {
        const user = imports.rejectUnauthorized([], request);
        response.json(imports.repository.findByLogin(user.login));
      });
})({

  express: require('express'),

  repository: require('./repository'),
  rejectUnauthorized: require('../../utils/reject-unauthorized')
});
