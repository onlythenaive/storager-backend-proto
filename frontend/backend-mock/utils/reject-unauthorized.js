(function (imports) {

  'use strict';

  module.exports = function (roles, request) {
    const user = request.user;
    if (user && user.enabled) {
      if (user.root) {
        return user;
      }
      if (!roles || roles.length === 0) {
        return user;
      }
      for (var i = 0; i < roles.length; i++) {
        if (user.roles.indexOf(roles[i]) > -1) {
          return user;
        }
      }
    }
    throw 'NOT_AUTHORIZED';
  };
})({});
