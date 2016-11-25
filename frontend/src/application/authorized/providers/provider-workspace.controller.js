(function () {

  'use strict';

  angular
        .module('application.authorized.providerWorkspace')
        .controller('providerWorkspaceController', [
          '$state',
          '$http',
          'dataUrlService',
          'logonService',
          'menuService',
          ProviderWorkspaceController
        ]);

  function ProviderWorkspaceController($state, $http, dataUrlService, logonService, menuService) {

    var self = this;

    menuService.update('PROVIDERS');

    self.admin = logonService.isAdmin();

    self.baseUrl = dataUrlService.getCompleteUrl('data/providers');

    self.add = function () {
      $state.go('application.authorized.providerNew');
    };

    var toPage = function (page) {
      self.page = page;
      $http
          .get(self.baseUrl, {
            params: {
              page: page,
              size: 10
            }
          })
          .then(function (result) {
            self.providers = result.data.items;
            self.totalPages = result.data.total;
          });
    };

    self.toDetailed = function (id) {
      $state.go('application.authorized.providerDetailed', {
        id: id
      });
    };

    self.toNextPage = function () {
      if (self.page < self.totalPages) {
        toPage(self.page + 1);
      }
    };

    self.toPreviousPage = function () {
      if (self.page > 1) {
        toPage(self.page - 1);
      }
    };

    toPage(1);
	
  }
})();
