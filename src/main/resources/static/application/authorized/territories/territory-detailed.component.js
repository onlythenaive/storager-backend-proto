(function () {

  'use strict';

  angular
        .module('application.authorized.territoryDetailed')
        .component('territoryDetailed', {
          controller: 'territoryDetailedController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/territories/territory-detailed.component.html'
        });
})();
