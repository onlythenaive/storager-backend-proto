(function () {

  'use strict';

  angular
        .module('application.authorized.providerDetailed')
        .component('providerDetailed', {
          controller: 'providerDetailedController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/providers/provider-detailed.component.html'
        });
})();
