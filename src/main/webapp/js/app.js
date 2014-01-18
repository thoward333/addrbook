'use strict';

/* App Module */

var addressbookApp = angular.module('addressbookApp', [
	'ngRoute',
	'addressbookControllers'
]);

addressbookApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/home', {
				templateUrl: 'partials/home.html',
				controller: 'HomeCtrl'
			}).
			otherwise({
				redirectTo: '/home'
			});
	}
]);
