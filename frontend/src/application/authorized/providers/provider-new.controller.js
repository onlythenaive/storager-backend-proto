(function () {

  'use strict';

  angular
        .module('application.authorized.providerNew')
        .controller('providerNewController', [
          '$state',
          '$http',
          'dataUrlService',
          'logonService',
          'menuService',
          ProviderNewController
        ]);

  function ProviderNewController($state, $http, dataUrlService, logonService, menuService) {

    var self = this;

    menuService.update('PROVIDERS');

    self.admin = logonService.isAdmin();
    self.baseUrl = dataUrlService.getCompleteUrl('data/providers');

    self.provider = {
      title: "Новая система",
      description: "новая система"
    };

    self.save = function () {
      $http
          .post(self.baseUrl, self.provider, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
            $state.go('application.authorized.providerDetailed', {id: result.data.id});
          });
    };

    self.cancel = function () {
      $state.go('application.authorized.providers');
    };
  }
})();
