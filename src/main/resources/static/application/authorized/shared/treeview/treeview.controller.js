(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview')
        .controller('treeviewController', [
          '$http',
          '$state',
          'dataUrlService',
          TreeviewController
        ]);

  function TreeviewController($http, $state, dataUrlService) {

    var self = this;

    self.baseUrl = dataUrlService.getCompleteUrl('data/' + self.resource);

    $http
        .get(self.baseUrl + '/roots')
        .then(function (result) {
          self.roots = result.data;
        });
  }
})();
