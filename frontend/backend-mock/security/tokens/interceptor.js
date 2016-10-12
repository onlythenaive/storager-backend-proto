(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  const interceptor = function (request, response, next) {
    const token = imports.tokenRepository.findById(imports.parseTokenId(request));
    request.user = imports.userRepository.findByLogin(token ? token.userLogin : 'guest');
    next();
  };

  function apply(server, routePrefix) {
    server.use(routePrefix + '/', interceptor);
  }
})({

  tokenRepository: require('./repository'),
  userRepository: require('../users/repository'),
  parseTokenId: require('../../utils/parse-token-id')
});
