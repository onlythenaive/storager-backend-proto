(function (imports) {

  'use strict';

  module.exports = imports.createRouter('/indicators', imports.service);
})({

  service: require('./service'),
  createRouter: require ('../utils/create-hierarchic-item-router')
});
