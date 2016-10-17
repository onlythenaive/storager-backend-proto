(function (imports) {

  'use strict';

  module.exports = function (name) {

    return imports.database.collection(name);
  };
})({

  database: require('./database'),
});
