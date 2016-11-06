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

    self.code = "0001";
    self.title = "Sample title";
  }
})();
