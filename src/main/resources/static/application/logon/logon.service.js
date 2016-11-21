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

    this.logon = function (login, secret) {
      return $http
                .post(dataUrlService.getCompleteUrl('security/logon'), {
                  login: login,
                  secret: secret
                })
                .success(function (authetication) {
                  $localStorage.authTokenId = authetication.token;
                });
    };
  }
})();
