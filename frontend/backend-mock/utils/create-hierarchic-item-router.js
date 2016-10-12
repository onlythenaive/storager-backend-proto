(function (imports) {

  'use strict';

  // NOTE: generic hierarchic item router factory
  module.exports = function (route, repository) {

    return {

      apply: apply
    };

    // NOTE: applies the router to specified server
    function apply(server, routePrefix) {

      var router = imports.express.Router()

          .use(imports.bodyParser.json())

          // NOTE: gets top-level items
          .get('/', function (request, response) {
            imports.rejectUnauthorized(['USER', 'ADMIN'], request);
            response.json(repository.getRoots());
          })

          // NOTE: gets an item by its code
          .get('/:code', function (request, response) {
            imports.rejectUnauthorized(['USER', 'ADMIN'], request);
            response.json(repository.get(request.params.code));
          })

          // NOTE: gets all descendants of an item with specified code
          .get('/:code/descendants', function (request, response) {
            imports.rejectUnauthorized(['USER', 'ADMIN'], request);
            response.json(repository.getDescendants(request.params.code));
          })

          // NOTE: adds a new item
          .post('/', function (request, response) {
            imports.rejectUnauthorized(['ADMIN'], request);
            response.json(repository.add(request.body));
          })

          // NOTE: updates an existing item
          .put('/:code', function (request, response) {
            imports.rejectUnauthorized(['ADMIN'], request);
            response.json(repository.update(request.params.code, request.body));
          })

          // NOTE: removes an existing item
          .delete('/:code', function (request, response) {
            imports.rejectUnauthorized(['ADMIN'], request);
            response.json(repository.remove(request.params.code));
          });

      server.use(routePrefix + route, router);
    }
  };
})({

  bodyParser: require('body-parser'),
  express: require('express'),

  rejectUnauthorized: require('./reject-unauthorized')
});
