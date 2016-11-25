(function () {

  'use strict';

  angular
        .module('application.authorized.shared')
        .service('indicatorService', [
		  '$http',
		  'dataUrlService',
          IndicatorService
        ]);

  function IndicatorService($http, dataUrlService) {

    var self = this;
	self.indicators = [];
	
	self.baseUrl = dataUrlService.getCompleteUrl('data/indicators');
	
	self.update = function(){
		getIndicatorsRoot();
		return self.indicators;
	};
	
	var getIndicatorsRoot = function(){
		  $http
          .get(self.baseUrl + '/roots')
          .then(function (result) {
            addToIndicatorsArray(result.data);
			
          });
	};
	var getIndicatorBranch = function(code){
		  $http
          .get(self.baseUrl + '/' + code + '/descendants')
          .then(function (result) {
            addToIndicatorsArray(result.data);			
          });
	};
	var addToIndicatorsArray = function(indicators){
		if(Array.isArray(indicators)){
			for(var i = 0;i<indicators.length; i++){
				self.indicators.push(indicators[i]);
				getIndicatorBranch(indicators[i].code)
			}
		}
	};
  }
})();
