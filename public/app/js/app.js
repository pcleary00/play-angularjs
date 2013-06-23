'use strict';


// Declare app level module which depends on filters, and services
angular.module('phonecat', ['phonecatServices']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/api/phones', {templateUrl: '/assets/app/partials/phone-list.html', controller: PhoneListCtrl});
    $routeProvider.when('/api/phones/:phoneId', {templateUrl: '/assets/app/partials/phone-detail.html', controller: PhoneDetailCtrl});
    $routeProvider.otherwise({redirectTo: '/api/phones'});
  }]);
