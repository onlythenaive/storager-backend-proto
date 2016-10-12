(function (imports) {

  'use strict';

  // NOTE: indicator router
  module.exports = imports.createRouter('/indicators', imports.repository);
})({

  repository: require('./repository'),
  createRouter: require ('../utils/create-hierarchic-item-router')
});
