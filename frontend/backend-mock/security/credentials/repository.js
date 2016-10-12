(function (imports) {

  'use strict';

  module.exports = {

    findAll: findAll,

    findByUserLogin: findByUserLogin
  };

  var credentials;

  function findAll() {
    return credentials;
  }

  function findByUserLogin(login) {
    return credentials
                    .find(function (credential) {
                      return credential.login === login;
                    });
  }

  credentials = [
    {
      login: "user",
      secret: "user123"
    },
    {
      login: "admin",
      secret: "admin123"
    }
  ];
})({});
