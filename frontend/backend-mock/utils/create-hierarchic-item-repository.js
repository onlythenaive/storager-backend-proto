(function (imports) {

  'use strict';

  module.exports = function (name) {

    const items = imports.database.collection(name);

    return {

      findByCode: findByCode,

      findDescendants: findDescendants,

      findRoots: findRoots,

      insert: insert,

      remove: remove,

      update: update
    };

    function findByCode(code) {
      return items.findOne({code: code});
    }

    function findDescendants(ascendantCode) {
      return items.find({ascendantCode: ascendantCode});
    }

    function findRoots() {
      return items.find({ascendantCode: null});
    }

    function insert(entity) {
      return items.insertOne(entity);
    }

    function remove(entity) {
      items.remove(entity);
    }

    function update(entity) {
      return items.update(entity);
    }
  };
})({

  database: require('./database'),
});
