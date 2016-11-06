(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview.treeviewItem')
        .controller('treeviewItemController', [
          '$http',
          '$state',
          'dataUrlService',
          TreeviewItemController
        ]);

  function TreeviewItemController($http, $state, dataUrlService) {

    var self = this;

    self.baseUrl = dataUrlService.getCompleteUrl('data/' + self.resource);

    self.expand = function () {

      $http
          .get(self.baseUrl + '/' + self.code + '/descendants')
          .then(function (result) {
            self.descendants = result.data;
          });
    };
  }
})();
