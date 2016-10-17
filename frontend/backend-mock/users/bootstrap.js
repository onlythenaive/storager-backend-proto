(function (imports) {

  'use strict';

  module.exports = {

    run: run
  };

  function run() {

    imports.service.bootstrap(user({
      login: "user",
      fullname: "Юзеров Ю. Ю.",
      enabled: true,
      roles: ["USER"]
    }));

    imports.service.bootstrap(user({
      login: "admin",
      fullname: "Админов А. А.",
      enabled: true,
      roles: ["USER", "ADMIN"]
    }));

    imports.service.bootstrap({
      login: "guest",
      secret: "guest",
      email: "support@iac.spb.ru",
      fullname: "Гостевой доступ",
      enabled: true,
      roles: ["GUEST"]
    });
  }

  function user(properties) {
    return {
      login: properties.login,
      secret: [properties.login, "123"].join(""),
      email: [properties.login, "@iac.spb.ru"].join(""),
      fullname: properties.fullname,
      enabled: properties.enabled,
      roles: properties.roles
    };
  }
})({

  service: require('./service')
});
