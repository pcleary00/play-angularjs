'use strict';

/* jasmine specs for controllers go here */

describe('MyCtrl1', function() {
	var myCtrl1;

	beforeEach(function() {
		myCtrl1 = new MyCtrl1();
	});

	it('should ....', function() {
		// spec body
	});
});

describe('MyCtrl2', function() {
	var myCtrl2;

	beforeEach(function() {
		myCtrl2 = new MyCtrl2();
	});

	it('should ....', function() {
		// spec body
	});
});

describe('PhoneCat controllers', function() {
	describe('PhoneListCtrl', function() {
		
		var scope, ctrl, $httpBackend;
		
		beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
			$httpBackend = _$httpBackend_;
			$httpBackend.expectGET('/phones').respond([{name: 'Nexus S'}, {name: 'Motorola DROID'}]);
			
			scope = $rootScope.$new();
			ctrl = $controller(PhoneListCtrl, {$scope: scope});
		}));
		
		it('should create "phones" model with 2 phones', function() {
			expect(scope.phones).toBeUndefined();
			$httpBackend.flush();
			
			expect(scope.phones.length).toBe(2);
		});
		
		it('should set the default value of orderProp model', function() {
			expect(scope.orderProp).toBe('age')
		});
	})
})
