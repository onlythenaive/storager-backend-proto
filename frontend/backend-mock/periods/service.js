(function (imports) {

  'use strict';

  module.exports = {

    bootstrap: bootstrap,

    getAll: getAll
  };

  function bootstrap(period) {
    return create(period);
  }

  function getAll() {
    return imports.repository
                            .find({})
                            .map(intoInfo);
  }

  function create(period) {
    return intoInfo(imports.repository.insertOne(period));
  }

  function intoInfo(entity) {
    return {
      code: entity.code,
      title: entity.title
    }
  }
})({

  repository: require('./repository')
});
