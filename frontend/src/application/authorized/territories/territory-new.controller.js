(function () {

  'use strict';

  angular
        .module('application.authorized.territoryNew')
        .controller('territoryNewController', [
           '$state',
           '$stateParams',
           '$http',
           'menuService',
           'dataUrlService',
          TerritoryNewController
        ]);

  function TerritoryNewController($state, $stateParams, $http, menuService, dataUrlService) {

    var self = this;

    menuService.update('TERRITORIES');

    var baseUrl = dataUrlService.getCompleteUrl('data/territories');

    self.territory = {
      ascendantCode: $stateParams['ascendantCode']
    };

    self.save = function () {
      $http
          .post(baseUrl, self.territory, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
            $state.go('application.authorized.territoryDetailed', {code: result.data.code});
          });
    };

    self.cancel = function () {
      $state.go('application.authorized.territories');
    };
  }
}) ();
