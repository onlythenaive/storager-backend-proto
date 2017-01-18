(function () {

  'use strict';

  angular
        .module('application.authorized.patchDetailed')
        .component('patchDetailed', {
          controller: 'patchDetailedController',
          controllerAs: 'it',
          templateUrl: 'static/application/authorized/patches/patch-detailed.component.html'
        });
})();
