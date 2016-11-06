(function () {

  'use strict';

  angular
        .module('application.authorized.shared.treeview')
        .controller('treeviewController', [
          '$http',
          '$state',
          TreeviewController
        ]);

  function TreeviewController($http, $state) {

    var self = this;

    self.resource = 'indicators';
    self.baseUrl = 'data/' + self.resource + '/';
    console.log(self.baseUrl);
    
    self.roots = [
      {
        code: '0001',
        title: 'Sample title 1',
        description: 'sample description 1',
        terminal: true
      },
      {
        code: '0002',
        title: 'Sample title 2',
        description: 'sample description 2',
        terminal: true
      },
      {
        code: '0003',
        title: 'Sample title 3',
        description: 'sample description 3',
        terminal: true
      }
    ];
  }
})();
