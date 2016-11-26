(function () {

  'use strict';

  angular
        .module('application.authorized.providerNew')
        .controller('providerNewController', [
          '$state',
          '$http',
          'dataUrlService',
          'logonService',
          'menuService',
		  'indicatorService',
          ProviderNewController
        ]);

  function ProviderNewController($state, $http, dataUrlService, logonService, menuService, indicatorService) {

    var self = this;

    menuService.update('PROVIDERS');

    self.admin = logonService.isAdmin();
    self.baseUrl = dataUrlService.getCompleteUrl('data/providers');
	self.allIndicators = indicatorService.update();

    self.provider = {
      title: "Новая система",
      description: "новая система"
    };

    self.save = function () {
		var grants = [];
		self.allIndicators.forEach(function (element){
			if(element.selected){
				grants.push(element.code);
			}
		});

      $http
          .post(self.baseUrl, self.provider, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {			  
				$http
					.put(self.baseUrl + '/' + result.data.id + '/grants', grants, {headers: {'Content-Type': 'application/json'}})
					.then(function(result){
						 $state.go('application.authorized.providerDetailed', {id: result.data.id});
					});
          });
    };

    self.cancel = function () {
      $state.go('application.authorized.providers');
    };
  }
})();
