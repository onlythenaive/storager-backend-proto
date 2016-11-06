(function () {

  'use strict';

  angular
        .module('application.authorized', [
          'application.authorized.menu',
          'application.authorized.footer',
          'application.authorized.indicatorWorkspace',
          'application.authorized.patchWorkspace',
          'application.authorized.territoryWorkspace',
          'application.authorized.providerWorkspace',
          'application.authorized.shared'
        ]);
}) ();
