# camel-spring-boot-rest
* This is an application to create a Sample REST API using Camel and Spring Boot. for below requirements.
This dockerized implementation provides RESTful API and apache camel routes for validation against json schema and
integrates message from backend service.

# Requirements:
* Expose a Restful Service
* HTTP verb as POST and Media Type can be JSON or XML
* Define/Create a front end and backend schema’s (JSON or XML Schema’s) – A simple schema should be sufficient.
* Validate a Message (using a front end JSON or XML Schema)
* Transform a Message (Front end JSON or XML format to a backend JSON or XML format)
* Validate a transformed message (using a backend JSON or XML Schema)
* Build a Mock Service to receive a message and return some sample response
* Unit Test
* Created a test that connects to the API you have created, through any Rest Client, and returns a response
* One happy path use case (End to End flow)
* One unhappy path use case (End to End flow)
* Once the exercise is completed then it would be great to create a Docker file to create an image of the microservice. (It’s optional, but preferred)

# Getting Started
To start, run on commandline:

`mvn spring-boot:run`

To start, run on Docker:
* From root of git checkout `mvn package`
* docker build -t camel-spring-boot-rest .
* To push to any other registry other than local refer `docker push`
* docker run -p 8080:8080 -t camel-spring-boot-rest:latest
* to stop container, `docker ps`
* docker stop `containerid from above step`

### Pre-requisites
Install JDK8
Intall Maven
Install Docker

### Invoke HTTP request

POST http request to:

`http://localhost:8080/vat`

Include the HEADER: Content-Type: application/json,

and a BODY Payload like:

`{"amount":10,"name":"aCompany","info":"company information"}`

The HTTP status code returned is 201 and the response as `{
                                                             "amount": 12,
                                                             "name": "aCompany",
                                                             "info": "Amount includes VAT"
                                                          }`
* Invoke with curl
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"amount":10,"name":"aCompany","info":"company information"}' \
  http://localhost:8080/vat

## Running  tests
mvn clean test
mvn clean install

### Reference Documentation
For further reference, please consider the following sections:
* [Apache Camel Getting Started](https://camel.apache.org/manual/latest/getting-started.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Using Apache Camel with Spring Boot](https://camel.apache.org/components/3.0.x/spring-boot.html)
* [Docker](https://docs.docker.com/engine/reference/commandline/docker/)
* [Spring Boot Docker](https://spring.io/guides/gs/spring-boot-docker/)

