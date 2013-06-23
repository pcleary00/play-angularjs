'use strict';

/* Controllers */

function MyCtrl1() {
}
MyCtrl1.$inject = [];

function MyCtrl2() {
}
MyCtrl2.$inject = [];

function PhoneListCtrl($scope, Phone) {

	$scope.phones = Phone.query();

	$scope.hello = "Hello, world!";

	$scope.orderProp = 'age';

}

function PhoneDetailCtrl($scope, $routeParams, Phone) {
	$scope.phone = Phone.get({
		phoneId : $routeParams.phoneId
	}, function(phone) {
		$scope.mainImageUrl = phone.images[0];
	});

	$scope.setImage = function(imageUrl) {
		$scope.mainImageUrl = imageUrl;
	}
}
