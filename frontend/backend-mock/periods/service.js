(function (imports) {

  'use strict';

  module.exports = {

    bootstrap: bootstrap,

    getAll: getAll
  };

  function bootstrap(properties) {
    const entity = intoEntity(properties);
    return intoInfo(imports.repository.insert(entity));
  }

  function getAll() {
    return imports.repository
                            .findAll()
                            .map(intoInfo);
  }

  function intoEntity(properties) {
    return {
      code: properties.code,
      title: properties.title
    };
  }

  function intoInfo(entity) {
    return {
      code: entity.code,
      title: entity.title
    };
  }
})({

  repository: require('./repository')
});
