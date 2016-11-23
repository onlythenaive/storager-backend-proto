(function () {

  'use strict';

  angular
        .module('application.authorized.patchDetailed')
        .controller('patchDetailedController', [
          '$stateParams',
          '$http',
          'dataUrlService',
          'menuService',
          PatchDetailedController
        ]);

  function PatchDetailedController($stateParams, $http, dataUrlService, menuService) {

    var self = this;

    menuService.update('PATCHES');

    var baseUrl = dataUrlService.getCompleteUrl('data/patches');
    var providerBaseUrl = dataUrlService.getCompleteUrl('data/providers');

    var id = $stateParams['id'];

    $http
        .get(baseUrl + '/' + id)
        .then(function (result) {
          self.patch = result.data;
          self.patch.createdAtParsed = new Date (self.patch.createdAt);
          $http
              .get(providerBaseUrl + '/' + result.data.providerId)
              .then(function (providerResult) {
                self.provider = providerResult.data;
              });
        });
  }
}) ();
