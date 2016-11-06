(function () {

  'use strict';

  angular
        .module('application.authorized.providerWorkspace')
        .controller('providerWorkspaceController', [
          '$http',
          ProviderWorkspaceController
        ]);

  function ProviderWorkspaceController($http) {

    var self = this;

    self.baseUrl = 'data/providers';

    var toPage = function (page) {
      self.page = page;
      $http
          .get(self.baseUrl)
          .then(function (result) {
            self.providers = result.data.items;
            self.totalPages = result.data.total;
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
