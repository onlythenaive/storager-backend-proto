(function (imports) {

  'use strict';

  // NOTE: territory router
  module.exports = imports.createService(imports.repository);
})({

  repository: require('./repository'),
  createService: require ('../utils/create-hierarchic-item-service')
});
