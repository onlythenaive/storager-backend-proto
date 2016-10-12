(function () {

  'use strict';

  angular
        .module('application.authorized', [
          'application.authorized.menu',
          'application.authorized.footer',
          'application.authorized.axisWorkspace',
          'application.authorized.indicatorWorkspace',
          'application.authorized.patchWorkspace',
          'application.authorized.providerWorkspace'
        ]);
}) ();
