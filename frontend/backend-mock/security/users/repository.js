(function (imports) {

  'use strict';

  module.exports = {

    findAll: findAll,

    findByLogin: findByLogin
  };

  var users;

  function findAll() {
    return users;
  }

  function findByLogin(login) {
    return users
                .find(function (user) {
                  return user.login === login;
                });
  }

  users = [
    {
      login: "guest",
      email: "guest@sample.email.com",
      fullname: "Гостевой доступ",
      registeredAt: imports.timestamp(),
      enabled: false,
      root: false,
      roles: ["GUEST"]
    },
    {
      login: "user",
      email: "user@sample.email.com",
      fullname: "Юзеров Ю. Ю.",
      registeredAt: imports.timestamp(),
      enabled: true,
      roles: ["USER"]
    },
    {
      login: "admin",
      email: "admin@sample.email.com",
      fullname: "Админов А. А.",
      registeredAt: imports.timestamp(),
      enabled: true,
      roles: ["ADMIN"]
    },
    {
      login: "root",
      email: "root@sample.email.com",
      fullname: "Доступ корневого уровня",
      registeredAt: imports.timestamp(),
      enabled: true,
      root: true,
      roles: ["ROOT"]
    }
  ];
})({

  timestamp: require('../../utils/timestamp')
});
