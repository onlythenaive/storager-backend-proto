(function () {

  'use strict';

  angular
        .module('application.logon')
        .component('logon', {
          controller: 'logonController',
          controllerAs: 'it',
          templateUrl: '/static/application/logon/logon.component.html'
        });
})();
