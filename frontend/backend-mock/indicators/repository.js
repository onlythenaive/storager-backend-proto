(function (imports) {

  'use strict';

  // NOTE: indicator repository
  module.exports = imports.createRepository([
    {
      code: "1",
      title: "Индикатор 1",
      description: "какое-то описание"
    },
    {
      code: "1.1",
      title: "Индикатор 1.1",
      terminal: true,
      parentCode: "1",
      description: "какое-то описание"
    },
    {
      code: "1.2",
      title: "Индикатор 1.2",
      terminal: true,
      parentCode: "1",
      description: "какое-то описание"
    },
    {
      code: "2",
      title: "Индикатор 2",
      terminal: true,
      description: "какое-то описание"
    }
  ]);
})({

  createRepository: require('../utils/create-hierarchic-item-repository')
});
