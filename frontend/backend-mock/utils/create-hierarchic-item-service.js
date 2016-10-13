(function (imports) {

  'use strict';

  // NOTE: generic hierarchic item repository factory
  module.exports = function (repository) {

    return {

      bootstrap: bootstrap,

      get: get,

      getDescendants: getDescendants,

      getRoots: getRoots,

      add: add,

      update: update,

      remove: remove
    };

    function bootstrap(properties) {
      repository.insert(properties);
    }

    function get(code) {
      return repository.find({code: code});
    }

    function getDescendants(code) {
      return repository.find({ascendantCode: code});
    }

    function getRoots() {
      return repository.find({ascendantCode: null});
    }

    function add(item) {
      /*
      if (get(item.code)) {
        throw "specified code already exists";
      }
      if (item.parentCode && !get(item.parentCode)) {
        throw "specified parent code does not exist";
      }
      item.terminal = true;
      items.push(item);
      return get(item.code);
      */
    }

    function update(code, properties) {
      /*
      const item = get(code);
      if (!item) {
        throw "specified code does not exist";
      }

      var parentItem = imports.nullOrUndefined (item.parentCode) ? null : get(item.parentCode);
      if (imports.nullOrUndefined(properties.parentCode)) {
        if (parentItem) {
          item.parentCode = null;
          if (getDescendants(parentItem.code).length == 0) {
            parentItem.terminal = true;
          }
        }
      } else {
        const newParentItem = get(properties.parentCode);
        if (!newParentItem) {
          throw "specified parent code does not exist";
        }
        if (parentItem) {
          if (parentItem.code !== newParentItem.code) {
            item.parentCode = newParentItem.code;
            if (getDescendants(parentItem.code).length == 0) {
              parentItem.terminal = true;
            }
          }
        } else {
          item.parentCode = newParentItem.code;
        }
      }
      item.title = properties.title;
      item.description = properties.description;
      return item;
      */
    }

    function remove(code) {
      /*
      const item = get(code);
      if (!item) {
        throw "specified code does not exist";
      }
      imports.processSubtree (items, code, function (item) {
        item.removed = true;
      });
      const parentItem = get(item.parentCode);
      if (parentItem) {
        parentItem.terminal = true;
      }
      */
    }
  };
})({

});
