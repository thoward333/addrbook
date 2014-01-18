'use strict';

/* Controllers */

var addressbookControllers = angular.module('addressbookControllers', []);

addressbookControllers.controller('HomeCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
	function($rootScope, $scope, $routeParams, $http) {
		$scope.createPerson = function() {
			console.log('BEGIN createPerson');
			
			$http.post('api/person', {
					"username": $scope.username
					,"firstName": $scope.firstName
					,"lastName": $scope.lastName
			})
			.success(function(data, status, headers, config) {
				console.log('data = ' , data);
				$scope.username = '';
				$scope.firstName = '';
				$scope.lastName = '';
				$scope.newUserId = data;
			})
			.error(function(data, status, headers, config) {
				console.log('error: data = ' , data);
			});
		};
		
		$scope.searchPerson = function() {
			$http.get('api/person/' + $scope.searchPersonId)
			.success(function(data, status, headers, config) {
				console.log('data = ' , data);
				$scope.person = data;
			})
			.error(function(data, status, headers, config) {
				console.log('error: data = ' , data);
			});
		};
	}
]);
