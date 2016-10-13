(function (imports) {

  'use strict';

  // NOTE: territory router
  module.exports = imports.createRouter('/territories', imports.service);
})({

  service: require('./service'),
  createRouter: require ('../utils/create-hierarchic-item-router')
});
