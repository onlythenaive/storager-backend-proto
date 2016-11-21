(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorWorkspace')
        .controller('indicatorWorkspaceController', [
           'menuService',
          IndicatorWorkspaceController
        ]);

  function IndicatorWorkspaceController(menuService) {

    menuService.update('INDICATORS');
  }
}) ();
