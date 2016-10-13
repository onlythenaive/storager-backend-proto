(function (imports) {

  'use strict';

  module.exports = imports.createService(imports.repository);
})({

  repository: require('./repository'),
  createService: require ('../utils/create-hierarchic-item-service')
});
