(function (imports) {

  'use strict';

  module.exports = {

  };

  imports.users.run();
})({

  indicators: require('./indicators/bootstrap'),
  periods: require('./periods/bootstrap'),
  territories: require('./territories/bootstrap'),
  users: require('./users/bootstrap')
});
