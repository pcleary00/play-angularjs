'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('PhoneCat App', function() {
	describe('Phone list view', function() {
		beforeEach(function() {
			browser().navigateTo('/angular')
		});
		
		it('should filter the phone list as user types into the search box', function() {
			expect(repeater('.phones li').count()).toBe(3);
			
			input('query').enter('nexus');
			expect(repeater('.phones li').count()).toBe(1);
			
			input('query').enter('motorola');
			expect(repeater('.phones li').count()).toBe(2);
		});
		
		it('should be possible to control phone order via the drop down select box', function() {
			input('query').enter('tablet');
			
			expect(repeater('.phones li', 'Phone List').column('phone.name')).toEqual(["Motorola XOOM with Wi-Fi", "Motorola XOOM"]);
			
			select('orderProp').option('Alphabetical');
			
			expect(repeater('.phones li', 'Phone List').column('phone.name')).toEqual(["Motorola XOOM", "Motorola XOOM with Wi-Fi"]);			
		})
	});
});


describe('my app', function() {

  beforeEach(function() {
    browser().navigateTo('../../app/index.html');
  });


  it('should automatically redirect to /view1 when location hash/fragment is empty', function() {
    expect(browser().location().url()).toBe("/view1");
  });


  describe('view1', function() {

    beforeEach(function() {
      browser().navigateTo('#/view1');
    });


    it('should render view1 when user navigates to /view1', function() {
      expect(element('[ng-view] p:first').text()).
        toMatch(/partial for view 1/);
    });

  });


  describe('view2', function() {

    beforeEach(function() {
      browser().navigateTo('#/view2');
    });


    it('should render view2 when user navigates to /view2', function() {
      expect(element('[ng-view] p:first').text()).
        toMatch(/partial for view 2/);
    });

  });
});
