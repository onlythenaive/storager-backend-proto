(function () {

  'use strict';

  angular
        .module('application.authorized.footer')
        .component('footer', {
          controller: 'footerController',
          controllerAs: 'it',
          templateUrl: '/static/application/authorized/footer/footer.component.html'
        });
})();
