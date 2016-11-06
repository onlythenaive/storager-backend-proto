(function () {

  'use strict';

  angular
        .module('application.authorized.menu')
        .controller('menuController', [
          '$http',
          '$localStorage',
          '$state',
          'dataUrlService',
          MenuController
        ]);

  function MenuController($http, $localStorage, $state, dataUrlService) {

    var self = this;

    $http
        .get(dataUrlService.getCompleteUrl('data/users/authenticated'))
        .then(function (result) {
          self.user = result.data;
        });

    this.logout = function () {
      $http
          .post(dataUrlService.getCompleteUrl('security/logout'))
          .then(function () {
            $localStorage.authTokenId = null;
            $state.go('application.logon');
          });
    };

    this.showIndicators = function () {
      $state.go('application.authorized.indicators');
    }

    this.showPatches = function () {
      $state.go('application.authorized.patches');
    }

    this.showPeriods = function () {
      $state.go('application.authorized.periods');
    }

    this.showProviders = function () {
      $state.go('application.authorized.providers');
    }

    this.showTerritories = function () {
      $state.go('application.authorized.territories');
    }
  }
})();
