'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers','demo.services' ]);

demoApp.constant("CONSTANTS", {
	getAllCards : "/creditcards",
	saveCard : "/creditcards"
});