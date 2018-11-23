'use strict';

angular.module('crudApp').controller('CardController',
    ['CardService', '$scope',  function( CardService, $scope) {

        var self = this;
        self.user = {};
        self.users=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.user.id === undefined || self.user.id === null) {
                console.log('Saving New Card', self.user);
                createUser(self.user);
            } else {
                updateUser(self.user, self.user.id);
                console.log('Card updated with id ', self.user.id);
            }
        }

        function createUser(user) {
            console.log('About to create user');
            CardService.createUser(user)
                .then(
                    function (response) {
                        console.log('Card created successfully');
                        self.successMessage = 'Card created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Credit Card');
                        self.errorMessage = 'Error while creating Credit Card: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function updateUser(user, id){
            console.log('About to update Credit Card');
            CardService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('Credit Card updated successfully');
                        self.successMessage='Credit Card updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Credit Card');
                        self.errorMessage='Error while updating Credit Card '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }

        function removeUser(id){
            console.log('About to remove Credit Card with id '+id);
            CardService.removeUser(id)
                .then(
                    function(){
                        console.log('Card '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing user '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return CardService.getAllUsers();
        }

        function editUser(id) {
            self.successMessage='';
            self.errorMessage='';
            CardService.getUser(id).then(
                function (user) {
                    self.user = user;
                },
                function (errResponse) {
                    console.error('Error while removing Credit Card ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }

    ]);