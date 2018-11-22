'use strict'

angular.module('demo.services', []).factory('CardService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.getAllCards = function() {
				return $http.get(CONSTANTS.getAllCards);
			}
			service.saveCard = function(cardDto) {
				return $http.post(CONSTANTS.saveCard, cardDto);
			}
			return service;
		} ]);