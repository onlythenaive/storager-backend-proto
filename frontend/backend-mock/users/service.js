(function (imports) {

  'use strict';

  module.exports = {

    bootstrap: bootstrap,

    getByLogin: getByLogin
  };

  function bootstrap(properties) {
    const entity = intoEntity(properties);
    return intoInfo(imports.repository.insert(entity));
  }

  function getByLogin(login) {
    return intoInfo(imports.repository.findByLogin(login));
  }

  function intoEntity(properties) {
    return properties;
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
