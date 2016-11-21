(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorWorkspace')
        .component('indicatorWorkspace', {
          controller: 'indicatorWorkspaceController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/indicators/indicator-workspace.component.html'
        });
})();
