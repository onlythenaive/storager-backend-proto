(function (imports) {

  'use strict';

  // NOTE: territory router
  module.exports = {

  };

  imports.service.bootstrap({
    code: "1001",
    ascendantCode: null,
    title: "Территория 1001",
    description: "какое-то описание"
  });
  imports.service.bootstrap({
    code: "1001.1",
    ascendantCode: "1001",
    title: "Территория 1001.1",
    terminal: true,
    description: "какое-то описание"
  });
  imports.service.bootstrap({
    code: "1001.2",
    ascendantCode: "1001",
    title: "Территория 1001.2",
    terminal: true,
    description: "какое-то описание"
  });
  imports.service.bootstrap({
      code: "1002",
      ascendantCode: null,
      title: "Территория 1002",
      terminal: true,
      description: "какое-то описание"
  });
})({

  service: require('./service')
});
