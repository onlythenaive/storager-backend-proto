(function () {

  'use strict';

  angular
        .module('application.authorized.periodWorkspace')
        .component('periodWorkspace', {
          controller: 'periodWorkspaceController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/periods/period-workspace.component.html'
        });
})();
