(function () {

  'use strict';

  angular
        .module('application.lost')
        .component('lost', {
          controller: 'lostController',
          controllerAs: 'it',
          templateUrl: '/static/application/lost/lost.component.html'
        });
})();
