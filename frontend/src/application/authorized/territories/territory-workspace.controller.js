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
  }
}) ();
