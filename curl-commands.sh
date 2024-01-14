curl --location --request POST 'localhost:8080/sites' \
--header 'Content-Type: application/json' \
--data-raw '{
"url":"demo.com"
}'

curl --location --request POST 'localhost:8080/questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"question": "Which team won the 2017 superbowl?",
"type": "1"
}'

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

curl --location --request POST 'localhost:8080/questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"question": "What is your favorite car brand?",
"type": "2"
}'

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

curl --location --request POST 'localhost:8080/questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"question": "What colors do you like?",
"type": "3"
}'

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

curl --location --request POST 'localhost:8080/matrix-questions' \
--header 'Content-Type: application/json' \
--data-raw '{
"siteId": 1,
"matrixQuestion": "Please tell us a bit about yourself?",
"rowHeader": "Age",
"columnHeader": "Gender"
}'

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

curl --location --request POST 'localhost:8080/sites/1/users' \
--data-raw ''