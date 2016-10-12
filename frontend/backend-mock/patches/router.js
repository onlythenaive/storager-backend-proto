(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  var router;

  function apply(server, routePrefix) {
    server.use(routePrefix + '/patches', router);
  }

  router = imports.express.Router()

      .use(imports.bodyParser.json())

      .get('/', function (request, response) {
        imports.rejectUnauthorized(['USER', 'ADMIN'], request);
        const paging = {
          offset: request.query.offset || 0,
          limit: request.query.limit || 20
        };
        const filtering = {
          providerTitle: request.query.providerTitle,
          status: request.query.status,
          since: request.query.since,
          until: request.query.until
        };
        response.json(imports.repository.findAll(paging, filtering));
      })

      .get('/:id', function (request, response) {
        imports.rejectUnauthorized(['USER', 'ADMIN'], request);
        response.json(imports.repository.findById(request.params.id));
      });
})({

  bodyParser: require('body-parser'),
  express: require('express'),

  repository: require('./repository'),
  rejectUnauthorized: require('../utils/reject-unauthorized')
});
