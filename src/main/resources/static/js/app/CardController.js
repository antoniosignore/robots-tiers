'use strict'

var module = angular.module('demo.controllers', []);
module.controller("CardController", [ "$scope", "CardService",
		function($scope, CardService) {

			$scope.cardDto = {
				name : null,
                creditCardNumber : null,
                creditLimit : null,
                remainingCredit: null
			};

			$scope.saveCard = function() {
                CardService.saveCard($scope.cardDto).then(function() {
					CardService.getAllCards().then(function(value) {
						$scope.allCards= value.data;
					}, function(reason) {
						console.log("error occured");
					}, function(value) {
						console.log("no callback");
					});

                    function cleanForm() {
                        $scope.cardDto = {
                            name: null,
                            creditCardNumber: null,
                            creditLimit: null,
                            remainingCredit: null
                        };
                    }

                    cleanForm();
                }, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		} ]);