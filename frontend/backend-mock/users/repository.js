(function (imports) {

  'use strict';

  module.exports = {

    findByLogin: findByLogin,

    insert: insert
  };

  const users = imports.database.collection('users');

  function findByLogin(login) {
    return users.findOne({login:login});
  }

  function insert(entity) {
    entity.registeredAt = imports.timestamp();
    return users.insertOne(entity);
  }
})({

  database: require('../utils/database'),
  timestamp: require('../utils/timestamp')
});
