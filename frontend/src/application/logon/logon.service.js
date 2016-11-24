(function () {

  'use strict';

  angular
        .module('application.logon')
        .service('logonService', [
          '$http',
          '$localStorage',
          'dataUrlService',
          LogonService
        ]);

  function LogonService($http, $localStorage, dataUrlService) {

    var self = this;

    this.logon = function (login, secret) {
      return $http
                .post(dataUrlService.getCompleteUrl('security/logon'), {
                  login: login,
                  secret: secret
                })
                .success(function (authentication) {
                  $localStorage.admin = authentication.roles.includes('ADMIN');
                  $localStorage.authTokenId = authentication.token;
                });
    };

    self.isAdmin = function () {
      return $localStorage.admin;
    };
  }
})();
