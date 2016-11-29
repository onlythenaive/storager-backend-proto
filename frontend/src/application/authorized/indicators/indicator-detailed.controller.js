(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorDetailed')
        .controller('indicatorDetailedController', [
           '$state',
           '$stateParams',
           '$http',
           'menuService',
           'logonService',
           'dataUrlService',
          IndicatorDetailedController
        ]);

  function IndicatorDetailedController($state, $stateParams, $http, menuService, logonService, dataUrlService) {

    var self = this;
    self.admin = logonService.isAdmin();
    menuService.update('INDICATORS');

    var baseUrl = dataUrlService.getCompleteUrl('data/indicators');
    var code = $stateParams['code'];
    $http
        .get(baseUrl + '/' + code)
        .then(function (result) {
          self.indicator = result.data;
        });

    self.save = function () {
      $http
          .put(baseUrl + '/' + code, self.indicator, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
            $state.go('application.authorized.indicators');
          });
    };
	
	self.delete = function () {
      $http
          .delete(baseUrl + '/' + code)
          .then(function (result) {
            $state.go('application.authorized.indicators');
          });
    };
  }
}) ();
