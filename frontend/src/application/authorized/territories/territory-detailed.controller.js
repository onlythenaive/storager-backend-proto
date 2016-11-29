(function () {

  'use strict';

  angular
        .module('application.authorized.territoryDetailed')
        .controller('territoryDetailedController', [
           '$state',
           '$stateParams',
           '$http',
           'menuService',
           'logonService',
           'dataUrlService',
          TerritoryDetailedController
        ]);

  function TerritoryDetailedController($state, $stateParams, $http, menuService, logonService, dataUrlService) {

    var self = this;
    self.admin = logonService.isAdmin();
    menuService.update('TERRITORIES');

    var baseUrl = dataUrlService.getCompleteUrl('data/territories');
    var code = $stateParams['code'];
    $http
        .get(baseUrl + '/' + code)
        .then(function (result) {
          self.territory = result.data;
        });

    self.save = function () {
      $http
          .put(baseUrl + '/' + code, self.territory, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
			  $state.go('application.authorized.territories');
            //console.log(result.data.code);
          });
    };
	
	self.delete = function () {
      $http
          .delete(baseUrl + '/' + code)
          .then(function (result) {
            $state.go('application.authorized.territories');
          });
    };
  }
}) ();
