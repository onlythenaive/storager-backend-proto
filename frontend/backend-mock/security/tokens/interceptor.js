(function (imports) {

  'use strict';

  module.exports = {

    apply: apply
  };

  const interceptor = function (request, response, next) {
    const token = imports.tokenRepository.findById(imports.parseTokenId(request));
    request.user = imports.userService.getByLogin(token ? token.login : 'guest');
    next();
  };

  function apply(server, routePrefix) {
    server.use(routePrefix + '/', interceptor);
  }
})({

  tokenRepository: require('./repository'),
  userService: require('../../users/service'),
  parseTokenId: require('../../utils/parse-token-id')
});
