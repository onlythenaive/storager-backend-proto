(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview.treeviewItem')
        .component('treeviewItem', {
          controller: 'treeviewItemController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/shared/treeview/treeview-item/treeview-item.component.html',
          bindings: {
            resource: '@',
            code: '@',
            title: '@',
            description: '@',
            terminal: '@'
          }
        });
})();
