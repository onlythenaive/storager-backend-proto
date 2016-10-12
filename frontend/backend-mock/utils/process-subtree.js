(function () {

  'use strict';

  // NOTE: require internal dependencies
  var notRemoved = require('./not-removed');

  // NOTE: processes a subtree of hierarchical items
  module.exports = function (items, code, processor) {
    items
        .filter(function (item) {
          return (item.code === code) && notRemoved (item);
        })
        .forEach(function (item) {
          processor(item);
          items.forEach(function (child) {
            if (child.parentCode === code) {
              processSubtree(items, child.code, processor);
            }
          });
        });
  };
})();
