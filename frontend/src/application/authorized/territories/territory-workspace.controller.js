(function () {

  'use strict';

  angular
        .module('application.authorized.territoryWorkspace')
        .controller('territoryWorkspaceController', [
          '$http',
          '$state',
          TerritoryWorkspaceController
        ]);

  function TerritoryWorkspaceController($http, $state) {

    var self = this;

    $http
        .get('/data/territories/roots')
        .then(function (result) {
          self.territories = result.data;
        });
  }
}) ();
