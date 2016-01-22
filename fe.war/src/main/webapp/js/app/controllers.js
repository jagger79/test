'use strict';

var phonecatController = angular.module('phonecatControllers', []);

phonecatController.controller('PhoneListCtrl', [ '$scope', '$http', '$route',
		'Phone', PhoneListController ]);

phonecatController.controller('PhoneDetailCtrl', [ '$scope', '$http', '$route', '$routeParams',
        'Phone', PhoneDetailController ]);

function PhoneListController(ngScope, ngHttp, ngRoute, Phone) {
	ngScope.phones = [ {
		'name' : 'Nexus S',
		'snippet' : 'Fast just got faster with Nexus S.',
		'age' : 1
	}, {
		'name' : 'Motorola XOOM™ with Wi-Fi',
		'snippet' : 'The Next, Next Generation tablet.',
		'age' : 2
	}, {
		'name' : 'MOTOROLA XOOM™',
		'snippet' : 'The Next, Next Generation tablet.',
		'age' : 3
	} ];
	//ngHttp.get('phones/phones.json').success(function(data) {
		//ngScope.phones = data;
	//});
	ngScope.phones = Phone.query();
	ngScope.name = "World";
	ngScope.orderProp = 'name';
}

function PhoneDetailController(ngScope, ngHttp, ngRoute, ngRouteParams, Phone) {
	ngScope.phoneId = ngRouteParams.phoneId;
	ngHttp.get('phones/' + ngRouteParams.phoneId + '.json').success(function(data) {
		ngScope.phone = data;
		ngScope.mainImageUrl = data.images[0];
    });
	ngScope.phone = Phone.get({phoneId: ngRouteParams.phoneId}, function(phone) {
		ngScope.mainImageUrl = phone.images[0];
	});
	ngScope.setImage = function(imageUrl) {
		ngScope.mainImageUrl = imageUrl;
    };
    ngScope.hello = function(name) {
        alert('Hello ' + (name || 'world') + '!');
    }
}