(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorWorkspace')
        .controller('indicatorWorkspaceController', [
           '$state',
           'logonService',
           'menuService',
          IndicatorWorkspaceController
        ]);

  function IndicatorWorkspaceController($state, logonService, menuService) {

  
	var self = this;
	
    menuService.update('INDICATORS');

    self.admin = logonService.isAdmin();

    self.addRootIndicator = function () {
      $state.go('application.authorized.indicatorNew');
    };
  }
}) ();
