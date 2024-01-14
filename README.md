### QuiZia RESTful API  (Backend) ###

QuiZia is a dynamic quiz application that serves unique questions to users via a RESTful API. The application is designed to be integrated into any website through a widget. The widget makes a REST call to the QuiZia API, providing a user UUID and a site UUID.

The QuiZia API is designed to always serve a unique question to a user as long as one exists. If a unique question does not exist, the API is designed to reset and start the list of questions a user has seen again.

The responses to the questions are captured and stored for each question served from the API. This allows for detailed tracking and analysis of user responses.

A user UUID is a unique value identifying the user that is generated on the page if one does not exist. This ensures that each user has a unique identifier, allowing for accurate tracking of user responses.

In the following sections, you will find details on the different types of questions QuiZia supports, how to start the project, and how to test the application.

## Built With

This project uses the following technologies:

- [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html): The main programming language used in the project.
- [Spring Boot](https://spring.io/projects/spring-boot): The framework used for creating the RESTful API.
- [Maven](https://maven.apache.org/): Dependency management and build tool.
- [H2 Database](https://www.h2database.com/html/main.html): In-memory database used for development and testing.
- [JUnit](https://junit.org/junit5/): Framework used for unit testing in Java.
- [Mockito](https://site.mockito.org/): Mocking framework used in conjunction with JUnit for unit testing.
- [Postman](https://www.postman.com/): Tool used for testing the API endpoints.

## Prerequisites

Before you begin, ensure you have met the following requirements:

* You have installed the latest version of [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
* You have a Windows/Linux/Mac machine with a recent version of the operating system.
* You have read the [Java Spring documentation](https://spring.io/projects/spring-boot#learn) to understand the basics of Spring Boot applications.
* You have installed a suitable IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [Eclipse](https://www.eclipse.org/downloads/), or [Visual Studio Code](https://code.visualstudio.com/download).
* You have installed [Postman](https://www.postman.com/downloads/) or another tool to test REST APIs.
* You are using the [H2 database](https://www.h2database.com/html/main.html) for data storage. H2 is a lightweight, in-memory database that is perfect for development and testing.
* You have a basic understanding of SQL databases and REST APIs.


## Setup and Installation to run the project

1. Clone the repository to your local machine.

git clone [repository URL](https://github.com/DoloresCode/Quiz)

2. Navigate to the project directory.

cd takehome

3. Install the projectt dependencies.

mvn install

4. Start the H2 Database. If you use the H2 console, navigate to http://localhost:8080/h2-console in your browser.

5. Run the project

Simply run the following maven command from the root directory of the project : 

```console
mvn spring-boot:run
```
You can also import the project into your favourite IDE and run the DemoApplication class.


## Testing

Insert test data by using the file curl-command.sh.
Navigate to the root directory of the project and run the following command :

```console
./curl-commands.sh
```

## Notes
Question types : 
1 -> Trivia
2 -> Poll
3 -> CheckBox


### Get the next available question

Using your favourite REST Client (Curl, PostMan, or other).

Hit the following GET REST API : localhost:8080/next-question/{userId}

Example using curl :
```console
curl --location --request GET 'localhost:8080/next-question/22' \
--data-raw ''
```
This query gets the next available question for the user with ID 22.

### Answer a Given question

Hit the following POST REST API : localhost:8080/answer-question

Example using curl :
```console
curl --location --request POST 'localhost:8080/answer-question' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId" : 22,
    "questionId" : 2,
    "matrixQuestionId" : 0,
    "questionAnswerId" : 3,
    "matrixQuestionRowId" : 0,
    "matrixQuestionColumnId" : 0
}'
```
Explanation : The user with id 22 has chosen the answer with id 3 to the question with id 2.