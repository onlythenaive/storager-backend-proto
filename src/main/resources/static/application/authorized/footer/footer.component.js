(function () {

  'use strict';

  angular
        .module('application.authorized.footer')
        .component('customFooter', {
          controller: 'footerController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/footer/footer.component.html'
        });
})();
