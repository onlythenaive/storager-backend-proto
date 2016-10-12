(function () {

  'use strict';

  angular
        .module('application.logon')
        .controller('logonController', [
          '$state',
          'logonService',
          LogonController
        ]);

  function LogonController($state, logonService) {

    var self = this;

    this.login;
    this.secret;

    this.logon = function () {
      logonService
              .logon(self.login, self.secret)
              .success(function () {
                $state.go('application.authorized.patches');
              })
              .error(function () {
                alert("Авторизация не выполнена, повторите попытку");
              });
    };
  }
})();
