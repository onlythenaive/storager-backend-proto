(function (imports) {

  'use strict';

  module.exports = {

    findAll: findAll,

    findById: findById
};

  var patches;

  function findAll(paging, filtering) {
    const filtered = patches
                          .filter(function (patch) {
                            return (
                              (!filtering.status || filtering.status === patch.status)
                              &&
                              (!filtering.since || unixTime(filtering.since) <= unixTime(patch.since))
                              &&
                              (!filtering.until || unixTime(filtering.until) >= unixTime(patch.until))
                              &&
                              (!filtering.providerTitle || filtering.providerTitle === imports.providerRepository.findById(patch.providerId).title)
                            );
                          });
    return {
      items: filtered.slice(paging.offset, paging.limit),
      total: filtered.length
    };
  }

  function findById(id) {
    return patches
                .find(function (patch) {
                  return patch.id === id;
                });
  }

  function unixTime(iso) {
    return Date.parse(iso).getTime();
  }

  patches = [
    {
      id: imports.uuid.v4(),
      comment: "Загрузка значений A",
      createdAt: imports.timestamp(),
      providerId: "1",
      status: "SUCCESS",
      indicatorInfos: [
        {
          indicatorId: "1001",
          totalPoints: 53
        },
        {
          indicatorId: "1002",
          totalPoints: 67
        }
      ]
    }
  ];
})({

  uuid: require('uuid'),

  providerRepository: require('../providers/repository'),
  timestamp: require('../utils/timestamp')
});
