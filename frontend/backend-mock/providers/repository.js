(function (imports) {

  'use strict';

  module.exports = {

    create: create,

    findAll: findAll,

    findById: findById,

    removeById: removeById,

    updateGrantsById: updateGrantsById,

    updateTokenById: updateTokenById
  };

  var idSequence = 1;

  var providers;

  function create(provider) {
    provider.id = id();
    provider.registeredAt = imports.timestamp();
    provider.token = imports.uuid.v4();
    provider.push(provider);
    return provider;
  }

  function findAll(paging) {
    return {
      items: providers.slice(paging.offset, paging.limit),
      total: providers.length
    };
  }

  function findById(id) {
    return providers
                  .find(function (provider) {
                    return provider.id === id && imports.notRemoved (provider);
                  });
  }

  function removeById(id) {
    providers
            .find(function (provider) {
              return provider.id === id;
            }).removed = true;
  }

  function updateGrantsById(id, grants) {
    const provider = providers
                            .find(function (provider) {
                              return provider.id === id && imports.notRemoved (provider);
                            });
    provider.grants = grants;
    return provider;
  }

  function updateTokenById(id) {
    const provider = providers
                            .find(function (provider) {
                              return provider.id === id && imports.notRemoved (provider);
                            });
    provider.token = imports.uuid.v4();
    return provider;
  }

  function id() {
    return "" + idSequence++;
  }

  providers = [
    {
      id: id(),
      title: "Provider A",
      registeredAt: imports.timestamp(),
      token: imports.uuid.v4(),
      grants: ["1001", "1002"]
    },
    {
      id: id(),
      title: "Provider B",
      registeredAt: imports.timestamp(),
      token: imports.uuid.v4(),
      grants: ["1001.1"]
    }
  ];
})({

  uuid: require('uuid'),

  notRemoved: require('../utils/not-removed'),
  timestamp: require('../utils/timestamp')
});
