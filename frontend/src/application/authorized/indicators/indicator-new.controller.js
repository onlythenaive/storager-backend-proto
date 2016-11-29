(function () {

  'use strict';

  angular
        .module('application.authorized.indicatorNew')
        .controller('indicatorNewController', [
           '$state',
           '$stateParams',
           '$http',
           'menuService',
           'dataUrlService',
          IndicatorNewController
        ]);

  function IndicatorNewController($state, $stateParams, $http, menuService, dataUrlService) {

    var self = this;

    menuService.update('INDICATORS');

    var baseUrl = dataUrlService.getCompleteUrl('data/indicators');

    self.indicator = {
      ascendantCode: $stateParams['ascendantCode']
    };

    self.save = function () {
      $http
          .post(baseUrl, self.indicator, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
			  $state.go('application.authorized.indicators');
            //$state.go('application.authorized.indicatorDetailed', {code: result.data.code});
          });
    };

    self.cancel = function () {
      $state.go('application.authorized.indicators');
    };
  }
}) ();
