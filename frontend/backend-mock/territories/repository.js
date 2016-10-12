(function (imports) {

  'use strict';

  // NOTE: territory repository
  module.exports = imports.createHierarchicItemRepository([
    {
      code: "1001",
      title: "Территория 1001"
    },
    {
      code: "1001.1",
      title: "Территория 1001.1",
      terminal: true,
      parentCode: "1001"
    },
    {
      code: "1001.2",
      title: "Территория 1001.2",
      terminal: true,
      parentCode: "1001"
    },
    {
      code: "1002",
      title: "Территория 1002",
      terminal: true
    }
  ]);
})({

  createHierarchicItemRepository: require('../utils/create-hierarchic-item-repository')
});
