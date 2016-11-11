(function () {

  'use strict';

  angular
        .module('application.authorized.patchWorkspace')
        .controller('patchWorkspaceController', [
          '$http',
          'dataUrlService',
          'menuService',
          PatchWorkspaceController
        ]);

  function PatchWorkspaceController($http, dataUrlService, menuService) {

    var self = this;

    menuService.update('PATCHES');

    var baseUrl = dataUrlService.getCompleteUrl('data/patches');
    var filter = {};

    var toPage = function (page) {
      self.page = page;
      $http
          .get(baseUrl, {
            params: {
              providerTitlePattern: filter.providerTitlePattern,
              status: filter.status,
              createdSince: filter.since,
              createdUntil: filter.until,
              page: self.page,
              size: 1
            }
          })
          .then(function (result) {
            self.patches = result.data.items;
            self.totalPages = result.data.total;
          });
    };

    self.toNextPage = function () {
      if (self.page < self.totalPages) {
        toPage(self.page + 1);
      }
    };

    self.toPreviousPage = function () {
      if (self.page > 1) {
        toPage(self.page - 1);
      }
    };

    self.applyFilter = function () {
      filter = {
        providerTitlePattern: self.providerTitlePattern,
        status: self.status !== 'ALL' ? self.status : null,
        since: self.since, // ...
        until: self.until, // ...
      };
      toPage(1);
    };

    self.resetFilter = function () {
      self.providerTitlePattern = null;
      self.status = 'ALL';
      self.since = null;
      self.until = null;
      self.applyFilter();
    };

    self.resetFilter();
  }
}) ();
