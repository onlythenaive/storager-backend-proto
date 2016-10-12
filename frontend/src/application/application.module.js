(function () {

  'use strict';

  angular
        .module('application', [
          'ngStorage',
          'ui.router',
          'application.logon',
          'application.lost',
          'application.authorized'
        ]);
}) ();
