(function (imports) {

  'use strict';

  module.exports = {

  };

  imports.service.bootstrap([
    {
      code: "DAY",
      title: "День"
    },
    {
      code: "WEEK",
      title: "Неделя"
    },
    {
      code: "MONTH",
      title: "Месяц"
    },
    {
      code: "QUARTER",
      title: "Квартал"
    },
    {
      code: "HALF_YEAR",
      title: "Полугодие"
    },
    {
      code: "YEAR",
      title: "Год"
    }
  ]);
})({

  service: require('./service')
});
