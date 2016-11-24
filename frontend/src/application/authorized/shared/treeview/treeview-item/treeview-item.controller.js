(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview.treeviewItem')
        .controller('treeviewItemController', [
          '$http',
          '$state',
          'dataUrlService',
          'logonService',
          TreeviewItemController
        ]);

  function TreeviewItemController($http, $state, dataUrlService, logonService) {

    var self = this;

    self.admin = logonService.isAdmin();
    self.baseUrl = dataUrlService.getCompleteUrl('data/' + self.resource);

    self.expanded = false;

    self.expand = function () {

      $http
          .get(self.baseUrl + '/' + self.code + '/descendants')
          .then(function (result) {
            self.descendants = result.data;
            self.expanded = true;
          });
    };

    self.fold = function () {
      self.descendants = [];
      self.expanded = false;
    };

    self.addChild = function () {
      $state.go(self.creation, {ascendantCode: self.code});
    };

    self.toDetailed = function () {
      console.log('detailed:' + self.detailed);
      $state.go(self.detailed, {code: self.code});
    };
  }
})();
