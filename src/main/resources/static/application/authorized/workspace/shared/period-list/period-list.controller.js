(function () {

  'use strict';

  angular
        .module('application.periodList')
        .controller('periodListController', [
          '$http',
          PeriodListController
        ]);

  function PeriodListController($http) {

    var self = this;

    this.periods;

    this.updatePeriods = updatePeriods;

    function updatePeriods() {
      $http
        .get('/data/periods')
        .then(function (result) {
          self.periods = result.data;
        });
    }
  }
})();
