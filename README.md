# qrok_test

I took Spring Security with (Basic Auth) (user = "viktor" password = "12345678") !                               
first of all change db-setting in application.properties

cUrl tests (Tested by POSTMAN) :
1) getAuthorById 
curl -X GET http://localhost:8080/author/1   -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4' \ 
-H 'cache-control: no-cache' -H 'postman-token: f1664438-42df-ee37-2e0c-278d1e107cfe'
  
2) add Author
curl -X POST   http://localhost:8080/author/add  -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4' \
  -H 'cache-control: no-cache' -H 'content-type: application/json' -H 'postman-token: d969a44e-47f7-bd70-f181-38ef641faf68' -d '{
"firstName":"Harry","lastName":"Potter","sex":"MALE","birthDate":"31-01-1992","rewards": [{"year":2009,"title":"First"}]
}'

3)endPoint 
curl -X GET  http://localhost:8080/author/info/short/1  -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4' -H 'cache-control: no-cache' \
  -H 'postman-token: 64538ad2-a974-21b4-0a77-ea6e925fc962'

4)update Author 
curl -X PUT   http://localhost:8080/author/update/ -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4' -H 'cache-control: no-cache' \
  -H 'content-type: application/json'  -H 'postman-token: 8d47971c-ff51-defb-f853-b11e9f90a18c'  -d '{
  "id":"1","firstName":"Jummy","lastName":"Twinkii","sex":"MALE","birthDate":"11-04-1965","rewards":
   [{"year":2017,"title":"Second"},{"year":2012,"title": "Third"}]
}'

5) add Book
curl -X POST  http://localhost:8080/book/add  -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4'  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'  -H 'postman-token: 0989d324-9aff-abfd-0980-1e67ca1650ec'-d '{
"title":"Flesh","isbn":"123445","genre":"FANTASY","authors":
[{"firstName":"Martin","lastName":"Binki","sex":"MALE","birthDate":"11-04-1985"}]
}'

6)getBookById 
curl -X GET  http://localhost:8080/book/1 -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4' -H 'cache-control: no-cache' \
  -H 'postman-token: 800e1352-e0bc-1365-ddb4-6e115d8c1aa9'
  
7)update Book
curl -X PUT  http://localhost:8080/book/update  -H 'authorization: Basic dmlrdG9yOjEyMzQ1Njc4' -H 'cache-control: no-cache' \
  -H 'content-type: application/json' -H 'postman-token: 837e79ea-a8e1-b953-7bd6-bda95d5987c8' -d '{
"id":"1","title":"The Lord Of The Rings","isbn":"757583","genre":"NOVEL","authors":
[{"firstName":"Kira","lastName":"Ryse","sex":"FEMALE","birthDate":"04-05-1986"}]
}'


