(function (imports) {

  'use strict';

  module.exports = function (name) {

    return imports.database.addCollection(name);
  };
})({

  database: require('./database'),
});
