(function () {

  'use strict';

  angular
        .module('application.authorized', [
          'application.authorized.menu',
          'application.authorized.footer',
          'application.authorized.indicatorNew',
          'application.authorized.indicatorDetailed',
          'application.authorized.indicatorWorkspace',
          'application.authorized.patchDetailed',
          'application.authorized.patchWorkspace',
          'application.authorized.territoryWorkspace',
          'application.authorized.providerNew',
          'application.authorized.providerDetailed',
          'application.authorized.providerWorkspace',
          'application.authorized.shared'
        ]);
}) ();
