(function () {

  'use strict';

  angular
        .module('application.authorized.providerNew')
        .component('providerNew', {
          controller: 'providerNewController',
          controllerAs: 'it',
          templateUrl: 'static/application/authorized/providers/provider-new.component.html'
        });
})();
