'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('phonecatServices', [ 'ngResource' ]).factory('Phone',
		function($resource) {
			return $resource('/api/phones/:phoneId', {}, {
				query : {
					method : 'GET',
					params : {
						phoneId : ''
					},
					isArray : true
				}
			});
		});
