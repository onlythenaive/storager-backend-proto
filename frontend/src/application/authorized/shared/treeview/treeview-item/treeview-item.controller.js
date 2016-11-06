(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview.treeviewItem')
        .controller('treeviewItemController', [
          '$http',
          '$state',
          TreeviewItemController
        ]);

  function TreeviewItemController($http, $state) {

    var self = this;

    self.baseUrl = 'data/' + self.resource + '/';

    self.expand = function () {

      $http
          .get(self.baseUrl + self.code + '/descendants')
          .then(function (result) {
            self.descendants = result.data;
          });
    };
  }
})();
