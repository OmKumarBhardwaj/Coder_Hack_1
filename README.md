
# CoderHack

## Introduction
CoderHack is a RESTful API service developed using Spring Boot, designed to manage the Leaderboard for a Coding Platform. It utilizes MongoDB for data persistence and is tailored for a specific coding contest with its unique leaderboard and award system.


## Features
- **CRUD Operations:** Handles create, read, update, and delete operations for user registrations in the coding contest.
- **User Registration:** Accepts User ID and Username for registration. New users start with a score of 0 and no badges.
- **Score Update:** Allows updating the user's score via PUT requests.
- **Badge Award System:** Users receive badges (Code Ninja, Code Champ, Code Master) based on their scores.
- **User Retrieval:** Retrieves users sorted by score, with a sorting time complexity of O(nlogn).
- **Error Handling:** Includes validation for fields and handles common HTTP errors.


## Endpoints
- `GET /users`: Retrieve all registered users.
- `GET /users/{userId}`: Retrieve details of a specific user.
- `POST /users`: Register a new user.
- `PUT /users/{userId}`: Update the score of a specific user.
- `DELETE /users/{userId}`: Deregister a specific user.

## Requirements
- Java JDK 17
- Spring Boot 3.2.3
- MongoDB (5.0.20 or above)
- Maven (3.9.3 or above)

#### Note : Install all the requirements before running the        project  

## Setup
To run this project locally, run the commands :- 
```
$ mvn clean
$ mvn install
$ mvn spring-boot:run
```

## Documentation
Further documentation is available in the code comments and JavaDoc format.

## Postman Collection

Enhance your experience with our API by utilizing the provided Postman collection. This collection is specifically designed to simplify interacting with the API's endpoints, offering a seamless way to test and explore its functionalities.

🔗 **Download the Postman Collection:** Access the collection directly from our repository - simply look for the file named `Code-a-thon.postman_collection.json`. This file contains pre-configured requests for all the endpoints, making it easier for you to get started with testing and integrating the API into your projects.

## Acknowledgements

- [Java JDK 17 Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [MongoDB Documentation](https://www.mongodb.com/docs/manual/)
- [Maven Documentation](https://maven.apache.org/guides/index.html)
- [REST API Best Practices](https://restfulapi.net/)
- [Global Exception Handling in Spring Boot](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Writing Effective READMEs](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)

