(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview')
        .controller('treeviewController', [
          '$http',
          '$state',
          TreeviewController
        ]);

  function TreeviewController($http, $state) {

    var self = this;

    self.baseUrl = 'data/' + self.resource + '/';

    $http
        .get(self.baseUrl + 'roots')
        .then(function (result) {
          self.roots = result.data;
        });
  }
})();
