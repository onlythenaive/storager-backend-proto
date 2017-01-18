(function () {

  'use strict';

  angular
        .module('application.authorized.territoryWorkspace')
        .component('territoryWorkspace', {
          controller: 'territoryWorkspaceController',
          controllerAs: 'it',
          templateUrl: 'static/application/authorized/territories/territory-workspace.component.html'
        });
})();
