(function () {

  'use strict';

  angular
        .module('application.authorized.menu')
        .component('menu', {
          controller: 'menuController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/menu/menu.component.html'
        });
})();
