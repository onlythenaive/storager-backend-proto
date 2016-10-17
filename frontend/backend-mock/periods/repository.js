(function (imports) {

  'use strict';

  module.exports = {

    findAll: findAll,

    findByCode: findByCode,

    insert: insert
  };

  const periods = imports.database.collection('periods');

  function findAll() {
    return periods.find({});
  }

  function findByCode(code) {
    return periods.find({code: code});
  }

  function insert(entity) {
    return periods.insertOne(entity);
  }
})({

  database: require('../utils/database')
});
