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

    menuService.update('INDICATORS');

    this.admin = logonService.isAdmin();

    this.addRootIndicator = function () {
      $state.go('application.authorized.indicatorNew');
    };
  }
}) ();
