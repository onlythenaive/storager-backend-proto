(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorDetailed')
        .component('indicatorDetailed', {
          controller: 'indicatorDetailedController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/indicators/indicator-detailed.component.html'
        });
})();
