(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview')
        .component('treeview', {
          controller: 'treeviewController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/shared/treeview/treeview.component.html',
          bindings: {
            resource: '@'
          }
        });
})();
