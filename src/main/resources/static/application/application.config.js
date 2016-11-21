(function () {

  'use strict';

  // TODO: decrease complexity of this configuration

  angular
        .module('application')
        .run(['$rootScope',  '$state', function ($rootScope, $state) {
          $rootScope.$state = $state;
        }])
        .service('dataUrlService', [
          DataUrlService
        ])
        .factory('httpRequestInterceptor', [
          '$localStorage',
          httpRequestInterceptor
        ])
        .config([
          '$httpProvider',
          configureHttpProvider
        ])
        .config([
          '$provide',
          configureHttp
        ])
        .config([
          '$locationProvider',
          configureLocationProvider
        ])
        .config([
          '$urlRouterProvider',
          configureUrlRouterProvider
        ])
        .config([
          '$stateProvider',
          configureStateProvider
        ]);

  function configureStateProvider($stateProvider) {

    $stateProvider

                  .state('application', {
                    abstract: true,
                    url: '',
                    views: {
                      '': {
                        component: 'application'
                      }
                    }
                  })

                  .state('application.logon', {
                    url: '/logon',
                    views: {
                      '@application': {
                        component: 'logon'
                      }
                    }
                  })

                  .state('application.authorized', {
                    abstract: true,
                    views: {
                      '@application': {
                        component: 'authorized'
                      }
                    }
                  })

                  .state('application.authorized.territories', {
                    url: '/territories',
                    views: {
                      '@application.authorized': {
                        component: 'territoryWorkspace'
                      }
                    }
                  })

                  .state('application.authorized.indicators', {
                    url: '/indicators',
                    views: {
                      '@application.authorized': {
                        component: 'indicatorWorkspace'
                      }
                    }
                  })

                  .state('application.authorized.patches', {
                    url: '/patches',
                    views: {
                      '@application.authorized': {
                        component: 'patchWorkspace'
                      }
                    }
                  })

                  .state('application.authorized.providers', {
                    url: '/providers',
                    views: {
                      '@application.authorized': {
                        component: 'providerWorkspace'
                      }
                    }
                  })

                  .state('application.lost', {
                    url: '*path',
                    views: {
                      '@application': {
                        component: 'lost'
                      }
                    }
                  });
  }

  function configureUrlRouterProvider($urlRouterProvider) {
    $urlRouterProvider.when('/', '/patches');
  }

  function configureLocationProvider($locationProvider) {
    $locationProvider.html5Mode(true);
  }

  function configureHttpProvider($httpProvider) {
    $httpProvider.interceptors.push('httpRequestInterceptor');
  }

  function httpRequestInterceptor($localStorage) {
    return {
      request: function (config) {
        config.headers['X-Auth-Token'] = $localStorage.authTokenId;
        return config;
      }
    };
  }

  function configureHttp($provide, $state) {

    $provide.decorator('$http', function($delegate, $injector) {

      var $localStorage = $injector.get('$localStorage');
      var $state = $injector.get('$state');

      function handleUnauthorizedError(error, status) {
        if (status === 401) {
          $state.go('application.logon');
        }
      }

      var originalDelete = $delegate.delete;

      $delegate.delete = function () {
        return originalDelete
                        .apply($delegate, arguments)
                        .error(handleUnauthorizedError);
      };

      var originalGet = $delegate.get;

      $delegate.get = function () {
        return originalGet
                        .apply($delegate, arguments)
                        .error(handleUnauthorizedError);
      };

      var originalPost = $delegate.post;

      $delegate.post = function () {
        return originalPost
                        .apply($delegate, arguments)
                        .error(handleUnauthorizedError);
      };

      var originalPut = $delegate.put;

      $delegate.put = function () {
        return originalPut
                        .apply($delegate, arguments)
                        .error(handleUnauthorizedError);
      };

      return $delegate;
    });
  }

  function DataUrlService() {

    var self = this;

    var explicitDataUrlPrefix = $('#dataBaseUrl').text();

    self.getCompleteUrl = function (url) {
      return explicitDataUrlPrefix ? explicitDataUrlPrefix + url : url;
    }
  }
})();
