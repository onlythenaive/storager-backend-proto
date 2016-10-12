(function () {

  'use strict';

  angular
        .module('application.authorized.providerWorkspace')
        .component('providerWorkspace', {
          controller: 'providerWorkspaceController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/providers/provider-workspace.component.html'
        });
})();
