(function (imports) {

  'use strict';

  // NOTE: territory router
  module.exports = imports.createRouter('/territories', imports.repository);
})({

  repository: require('./repository'),
  createRouter: require ('../utils/create-hierarchic-item-router')
});
