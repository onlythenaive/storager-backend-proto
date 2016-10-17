(function (imports) {

  'use strict';

  module.exports = {

    collection: collection
  };

  const loki = new imports.loki();

  function collection(name) {
    const existing = loki.getCollection(name);
    return existing ? existing : loki.addCollection(name);
  }
})({

  loki: require('lokijs'),
});
