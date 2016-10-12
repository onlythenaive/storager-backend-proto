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

  function findByUserLogin(userLogin) {
    return credentials
                    .find(function (credential) {
                      return credential.userLogin === userLogin;
                    });
  }

  credentials = [
    {
      userLogin: "user",
      userSecret: "user123"
    },
    {
      userLogin: "admin",
      userSecret: "admin123"
    },
    {
      userLogin: "root",
      userSecret: "root"
    }
  ];
})({});
