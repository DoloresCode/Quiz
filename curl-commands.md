## Create site
curl --location --request POST 'localhost:8080/sites' \
--header 'Content-Type: application/json' \
--data-raw '{
"url":"demo.com"
}'

## Create trivia question
curl --location --request POST 'localhost:8080/questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"question": "Which team won the 2017 superbowl?",
"type": "1"
}'
### Create trivia answers
curl --location --request POST 'http://localhost:8080/questions/2/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Falcons",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/2/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Patriots",
"isCorrectAnswer": true
}'

## Create Pool question
curl --location --request POST 'localhost:8080/questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"question": "What is your favorite car brand?",
"type": "2"
}'


### Create pool answers
curl --location --request POST 'http://localhost:8080/questions/5/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Nissan",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/5/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Honda",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/5/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Audi",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/5/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "BMW",
"isCorrectAnswer": false
}'

## Create Checkbox Question
curl --location --request POST 'localhost:8080/questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"question": "What colors do you like?",
"type": "3"
}'
### Create Checkbox answers
curl --location --request POST 'http://localhost:8080/questions/10/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Red",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/10/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Blue",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/10/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Green",
"isCorrectAnswer": false
}'
curl --location --request POST 'http://localhost:8080/questions/10/answers' \
--header 'Content-Type: application/json' \
--data-raw '{
"answer": "Black",
"isCorrectAnswer": false
}'
## Create Matrix question
curl --location --request POST 'localhost:8080/matrix-questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"matrixQuestion": "Please tell us a bit about yourself?",
"rowHeader": "Age",
"columnHeader": "Gender"
}'
### Create Matrix question rows
curl --location --request POST 'localhost:8080/matrix-questions/15/rows' \
--header 'Content-Type: application/json' \
--data-raw '{
"row" : "< 18",
"index" : 1
}'
curl --location --request POST 'localhost:8080/matrix-questions/15/rows' \
--header 'Content-Type: application/json' \
--data-raw '{
"row" : "18 to 35",
"index" : 2
}'
curl --location --request POST 'localhost:8080/matrix-questions/15/rows' \
--header 'Content-Type: application/json' \
--data-raw '{
"row" : "35 to 55",
"index" : 3
}'
curl --location --request POST 'localhost:8080/matrix-questions/15/rows' \
--header 'Content-Type: application/json' \
--data-raw '{
"row" : "> 55",
"index" : 4
}'
### Create Matrix question columns
curl --location --request POST 'localhost:8080/matrix-questions/15/columns' \
--header 'Content-Type: application/json' \
--data-raw '{
"column" : "Male",
"index" : 1
}'
curl --location --request POST 'localhost:8080/matrix-questions/15/columns' \
--header 'Content-Type: application/json' \
--data-raw '{
"column" : "Female",
"index" : 2
}'

## Create new User
curl --location --request POST 'localhost:8080/sites/1/users' \
--data-raw ''

## Get Next Question for a User
curl --location --request GET 'localhost:8080/next-question/22' \
--data-raw ''

## Post a User Response
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