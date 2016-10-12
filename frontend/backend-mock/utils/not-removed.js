(function () {

  'use strict';

  // NOTE: checks if item is not soft-removed
  module.exports = function (object) {
    return !object.removed;
  };
})();
