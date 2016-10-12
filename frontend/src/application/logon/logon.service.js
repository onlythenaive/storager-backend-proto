(function () {

  'use strict';

  angular
        .module('application.logon')
        .service('logonService', [
          '$http',
          '$localStorage',
          LogonService
        ]);

  function LogonService($http, $localStorage) {

    this.logon = function (login, secret) {
      return $http
                .post('/security/logon', {
                  login: login,
                  secret: secret
                })
                .success(function (token) {
                  $localStorage.authTokenId = token.id;
                });
    };
  }
})();
