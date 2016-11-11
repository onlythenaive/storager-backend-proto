(function () {

  'use strict';

  angular
        .module('application.authorized.menu')
        .service('menuService', [
          MenuService
        ]);

  function MenuService() {

    var self = this;

    self.updateHandler = null;

    self.update = function (state) {
      if (self.updateHandler) {
        self.updateHandler(state);
      }
    }
  }
})();
