(function () {

  'use strict';

  angular
        .module('application.authorized.territoryWorkspace')
        .controller('territoryWorkspaceController', [
          '$http',
          '$state',
          'menuService',
          TerritoryWorkspaceController
        ]);

  function TerritoryWorkspaceController($http, $state, menuService) {

    var self = this;
    menuService.update('TERRITORIES');
  }
}) ();
