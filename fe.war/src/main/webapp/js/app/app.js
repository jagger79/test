'use strict';

var phonecatApp = angular.module('phonecatApp', [
	'ngRoute',
	'phonecatControllers',
	'phonecatFilters',
	'phonecatServices',
	'phonecatAnimations'
]);

phonecatApp.config(['$routeProvider',
    function($routeProvider) {
      $routeProvider.
        when('/phones', {
          templateUrl: 'phone-list.html',
          controller: 'PhoneListCtrl'
        }).
        when('/phones/:phoneId', {
          templateUrl: 'phone-detail.html',
          controller: 'PhoneDetailCtrl'
        }).
        otherwise({
          redirectTo: '/phones'
        });
}]);

/* Builds the updated table for the member list */
function test(members) {
	var x;
	x = 3.14;
    return _.template( $( "#member-tmpl" ).html(), {"members": members});
}

//test();