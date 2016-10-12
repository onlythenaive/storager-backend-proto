(function (imports) {

  'use strict';

  module.exports = function (request) {
    return request.get('X-Auth-Token')
  };
})({});
