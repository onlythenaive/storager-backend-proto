(function (imports) {

  'use strict';

  module.exports = {

    create: create,

    findAll: findAll,

    findById: findById,

    findByUserLogin: findByUserLogin,

    removeAll: removeAll,

    removeById: removeById,

    removeByUserLogin: removeByUserLogin
  };

  var tokens;

  function create(userLogin) {
    const token = {
      id: imports.uuid.v4(),
      createdAt: imports.timestamp(),
      userLogin: userLogin
    };
    tokens.push(token);
    return token;
  }

  function findAll() {
    return tokens;
  }

  function findById(id) {
    return tokens
                .find(function (token) {
                  return token.id === id;
                });
  }

  function findByUserLogin(userLogin) {
    return tokens
                .find(function (token) {
                  return token.userLogin === userLogin;
                });
  }

  function removeAll() {
    reset();
  }

  function removeById(id) {
    tokens = tokens
                  .filter(function (token) {
                    return token.id !== id;
                  });
  }

  function removeByUserLogin(userLogin) {
    tokens = tokens
                  .filter(function (token) {
                    return token.userLogin !== userLogin;
                  });
  }

  function reset() {
    tokens = [];
  }

  reset();
})({

  uuid: require('uuid'),

  timestamp: require('../../utils/timestamp')
});
