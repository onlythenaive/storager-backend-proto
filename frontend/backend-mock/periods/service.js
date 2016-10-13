(function (imports) {

  'use strict';

  module.exports = {

    bootstrap: bootstrap,

    getAll: getAll
  };

  function bootstrap(period) {
    return imports.repository.insert(period);
  }

  function getAll() {
    return imports.repository.find({});
  }
})({

  repository: require('./repository')
});
