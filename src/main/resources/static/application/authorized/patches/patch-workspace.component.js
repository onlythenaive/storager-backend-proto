(function () {

  'use strict';

  angular
        .module('application.authorized.patchWorkspace')
        .component('patchWorkspace', {
          controller: 'patchWorkspaceController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/patches/patch-workspace.component.html'
        });
})();
