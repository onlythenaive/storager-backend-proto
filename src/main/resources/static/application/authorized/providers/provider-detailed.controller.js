(function () {

  'use strict';

  angular
        .module('application.authorized.providerDetailed')
        .controller('providerDetailedController', [
          '$state',
          '$stateParams',
          '$http',
          'dataUrlService',
          'logonService',
		  'menuService',
		  'indicatorService',
          ProviderDetailedController
        ]);

  function ProviderDetailedController($state, $stateParams, $http, dataUrlService, logonService, menuService, indicatorService) {

    var self = this;

    menuService.update('PROVIDERS');

    self.admin = logonService.isAdmin();
    var baseUrl = dataUrlService.getCompleteUrl('data/providers');
    var id = $stateParams['id'];
	

    $http
        .get(baseUrl + '/' + id)
        .then(function (result) {
          self.provider = result.data;
          self.provider.registeredAtParsed = new Date (self.provider.registeredAt);
		  self.allIndicators = indicatorService.update(self.provider.grants);
          if (self.admin) {
            $http
                .get(baseUrl + '/' + id + '/token')
                .then(function (tokenResult) {
                  self.providerToken = tokenResult.data.token;
                });
          }
        });

    self.save = function () {
		var grants = [];
		self.allIndicators.forEach(function (element){
			if(element.selected){
				grants.push(element.code);
			}
		});
      $http
          .put(baseUrl + '/' + id, self.provider, {headers: {'Content-Type': 'application/json'}})
          .then(function (result) {
            if (self.admin) {
              // TODO: grants
			  		$http
					.put(baseUrl+ '/' + id +  '/grants', grants, {headers: {'Content-Type': 'application/json'}})
					.then(function (result){
						$state.go('application.authorized.providers');
					});
            }
          });
    };
	
	 self.delete = function () {
      $http
          .delete(baseUrl + '/' + id)
          .then(function (result) {
				$state.go('application.authorized.providers');
          });
    };

    self.cancel = function () {
      $state.go('application.authorized.providers');
    };
  }
})();
