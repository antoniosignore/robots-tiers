# Sapient Credit Card Code test 

## Design

This is project implements the credit card code test as descriebd in the document Test.docx adopting 
the simple very common architectural pattern : Stacked multitier layer architecture.

In software engineering, multitier architecture (often referred to as n-tier architecture) or 
multilayered architecture is a client–server architecture in which presentation, business logic, 
and data management functions are physically separated. 

The client is a very simple Angular UX that mimics the user mock as in the pic: 

    ui-screen.png

This project is of course not to be considered in any form or shape as production ready.

I also, as suggested in the document did not push too much on the code coverage testing.

The test has been designed with the intent to showcase the implementation of some useful NFR 
(non functional requirements) as well as provide the expected results, of course.

The feature set includes:

## Swagger definition : 

    http://localhost:8080/swagger-ui.html

## Log aspect: each method is traced with its actual parameters

* Stacked layer design composed of:

    - 2.0.2.RELEASE (Java)
    - controller layer spring MVC with RestControllers
    - service layer
    - spring data repositories
    - hibernate persistence
    - DTO beans for rest i/o
    - Mapper to convert entito/dto

## Installation

This project requires JDK 8 and maven for building and testing it. 

Install Java on linux using this command:

    sudo apt-get install openjdk-8-jdk
    
Install maven    
    
    sudo apt-get install maven
 
## How to build
 
    mvn clean package

Code coverage is executed by Jacoco in the build process. 

The results are in an HTML page generated under :
    
    target/site/jacoco/index.html

## How to test

    mvn clean test
    
Test results are located at:
    
    target/surefire-reports    

## how to run it:

    java -jar target/creditcard-0.0.1.jar
    
    (make sure port 8080 is available)
    
(If I had time I would have preferred packing in a docker image)
  
  
## Angular simple client:

    http://localhost:8080/home  
  
     
# REST CURL

## Valid card numbers
    12345678903555
    012850003580200
    5497083002781388334

## Create a valid credit card

    curl --request POST  --url http://localhost:8080/creditcards \
	--header 'content-type: application/json'  --header 'Accept: application/json' \
	--data '{"creditCardNumber":"5497083002781388334","name":"antonio","creditLimit":100.0}'

    {
        "creditCardNumber":"5497083002781388334",
        "name":"antonio","creditLimit":100.0,
        "remainingCredit":100.0
    }

## Create an invalid Luhn 10 credit card

    curl --request POST  --url http://localhost:8080/creditcards \
	--header 'content-type: application/json'  --header 'Accept: application/json' \
	--data '{"creditCardNumber":"5497083002781388333","name":"antonio","creditLimit":100.0}'
     
     {
       "message" : "Credit card number is not valid"
     }
     
## Get all credit cards

    curl -X GET --header 'Accept: application/json' 'http://localhost:8080/creditcards'   
    
     