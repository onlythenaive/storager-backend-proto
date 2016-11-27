(function () {

  'use strict';

  angular
        .module('application.authorized.territoryWorkspace')
        .controller('territoryWorkspaceController', [
          '$http',
          '$state',
          'menuService',
		  'logonService',
          TerritoryWorkspaceController
        ]);

  function TerritoryWorkspaceController($http, $state, menuService, logonService) {

    var self = this;
	
    menuService.update('TERRITORIES');
	
	self.admin = logonService.isAdmin();

    self.addRootTerritory = function () {
      $state.go('application.authorized.territoryNew');
    };
  }
}) ();
