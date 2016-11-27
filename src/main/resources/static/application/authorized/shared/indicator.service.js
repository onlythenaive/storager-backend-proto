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
	
	var selectedCodes = [];
	
	self.update = function(toSelect){
		(toSelect)?selectedCodes = toSelect:selectedCodes=[];
		self.indicators = []
		getIndicatorsTree();
		return self.indicators;
	};
	
	var getIndicatorsTree = function(){
		  $http
          .get(self.baseUrl + '/roots')
          .then(function (result) {
			  if(Array.isArray(result.data)){
				  var ind =  result.data.shift();				  
				  getIndicatorBranch(ind,result.data);
			  }
          });
	};
	
	var getIndicatorBranch = function(ind, arrayToGo){	
		selectIndicator(ind);
		self.indicators.push(ind);	
		  $http
          .get(self.baseUrl + '/' + ind.code + '/descendants')
          .then(function (result) {			  
			if(Array.isArray(result.data)&&result.data.length>0){				
				var newInd = result.data.shift();
				getIndicatorBranch(newInd, result.data.concat(arrayToGo));
			}else if(arrayToGo.length>0){
				var newInd = arrayToGo.shift();
				getIndicatorBranch(newInd, arrayToGo);
			}
          });
	};
	var selectIndicator = function(ind){
		(selectedCodes.indexOf(ind.code)>-1)?ind.selected = true:ind.selected = false;
	}
  }
})();
