(function (imports) {

  'use strict';

  module.exports = {

    bootstrap: bootstrap,

    getByLogin: getByLogin
  };

  function bootstrap(user) {
    return create(user);
  }

  function create(user) {
    return intoInfo(imports.repository.insertOne(user));
  }

  function getByLogin(login) {
    return intoInfo(imports.repository.findOne({login: login}));
  }

  function intoInfo(entity) {
    return {
      login: entity.login,
      email: entity.email,
      fullname: entity.fullname,
      registeredAt: entity.registeredAt,
      enabled: entity.enabled,
      roles: entity.roles
    };
  }
})({

  repository: require('./repository')
});
