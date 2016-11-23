(function () {

  'use strict';

  angular
        .module('application.authorized.providerDetailed')
        .controller('providerDetailedController', [
          '$state',
          '$stateParams',
          '$http',
          'dataUrlService',
          'logonService',
          'menuService',
          ProviderDetailedController
        ]);

  function ProviderDetailedController($state, $stateParams, $http, dataUrlService, logonService, menuService) {

    var self = this;

    menuService.update('PROVIDERS');

    self.admin = logonService.isAdmin();
    var baseUrl = dataUrlService.getCompleteUrl('data/providers');
    var id = $stateParams['id'];

    $http
        .get(baseUrl + '/' + id)
        .then(function (result) {
          self.provider = result.data;
          self.provider.registeredAtParsed = new Date (self.provider.registeredAt);
          if (self.admin) {
            $http
                .get(baseUrl + '/' + id + '/token')
                .then(function (tokenResult) {
                  self.providerToken = tokenResult.data.token;
                });
          }
        });

    self.save = function () {
      $http
          .put(self.baseUrl, self.provider, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
            if (self.admin) {
              // TODO: grants
            }
          });
    };

    self.cancel = function () {
      $state.go('application.authorized.providers');
    };
  }
})();
