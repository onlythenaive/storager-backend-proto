(function (imports) {

  'use strict';

  module.exports = imports.database.collection('users');
})({

  database: require('../utils/database')
});
