(function () {

  'use strict';

  angular
        .module('application')
        .component('application', {
          controller: 'applicationController',
          controllerAs: 'it',
          templateUrl: '/static/application/application.component.html'
        });
})();
