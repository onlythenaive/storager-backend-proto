(function () {

  'use strict';

  angular
        .module('application.authorized')
        .component('authorized', {
          controller: 'authorizedController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/authorized.component.html'
        });
})();
