(function () {

  'use strict';

  angular
        .module('application.authorized.menu')
        .controller('menuController', [
          '$http',
          '$localStorage',
          '$state',
          MenuController
        ]);

  function MenuController($http, $localStorage, $state) {

    var self = this;

    $http
        .get('/data/users/authenticated')
        .then(function (result) {
          self.user = result.data;
        });

    this.logout = function () {
      $http
          .post('/security/logout')
          .then(function () {
            $localStorage.authTokenId = null;
            $state.go('application.logon');
          });
    };

    this.showAxes = function () {
      $state.go('application.authorized.axes');
    }

    this.showIndicators = function () {
      $state.go('application.authorized.indicators');
    }

    this.showPatches = function () {
      $state.go('application.authorized.patches');
    }

    this.showProviders = function () {
      $state.go('application.authorized.providers');
    }
  }
})();
