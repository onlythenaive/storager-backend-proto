(function (imports) {

  'use strict';

  module.exports = {

  };

  imports.service.bootstrap({
    code: "DAY",
    title: "День"
  });

  imports.service.bootstrap({
    code: "WEEK",
    title: "Неделя"
  });

  imports.service.bootstrap({
    code: "MONTH",
    title: "Месяц"
  });

  imports.service.bootstrap({
    code: "QUARTER",
    title: "Квартал"
  });

  imports.service.bootstrap({
    code: "HALF_YEAR",
    title: "Полугодие"
  });

  imports.service.bootstrap({
    code: "YEAR",
    title: "Год"
  });
})({

  service: require('./service')
});
