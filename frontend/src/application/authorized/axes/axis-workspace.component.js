(function () {

  'use strict';

  angular
        .module('application.authorized.axisWorkspace')
        .component('axisWorkspace', {
          controller: 'axisWorkspaceController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/axes/axis-workspace.component.html'
        });
})();
