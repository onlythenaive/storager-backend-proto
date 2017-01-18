(function () {

  'use strict';

  angular
        .module('application.authorized.territoryNew')
        .component('territoryNew', {
          controller: 'territoryNewController',
          controllerAs: 'it',
          templateUrl: 'static/application/authorized/territories/territory-new.component.html'
        });
})();
