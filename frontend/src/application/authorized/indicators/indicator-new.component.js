(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorNew')
        .component('indicatorNew', {
          controller: 'indicatorNewController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/indicators/indicator-new.component.html'
        });
})();
