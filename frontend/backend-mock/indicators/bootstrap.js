(function (imports) {

  'use strict';

  module.exports = {

  };

  imports.service.bootstrap({
    code: "1",
    ascendantCode: null,
    title: "Индикатор 1",
    description: "какое-то описание",
  });

  imports.service.bootstrap({
    code: "1.1",
    ascendantCode: "1",
    title: "Индикатор 1.1",
    terminal: true,
    description: "какое-то описание"
  });

  imports.service.bootstrap({
    code: "1.2",
    ascendantCode: "1",
    title: "Индикатор 1.2",
    terminal: true,
    description: "какое-то описание"
  });

  imports.service.bootstrap({
    code: "2",
    ascendantCode: null,
    title: "Индикатор 2",
    terminal: true,
    description: "какое-то описание"
  });
})({

  service: require('./service')
});
