(function () {

  'use strict';

  angular
        .module('application.authorized.periodWorkspace')
        .controller('periodWorkspaceController', [
          '$http',
          '$state',
          PeriodWorkspaceController
        ]);

  function PeriodWorkspaceController($http, $state) {

    var self = this;

    $http
        .get('/data/periods')
        .then(function (result) {
          self.periods = result.data;
        });
  }
}) ();
