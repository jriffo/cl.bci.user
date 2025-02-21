# cl.bci.user
BCI Application Test

### Installation

1. Clone the repository
   ```sh
   git clone https://github.com/jriffotoro/cl.bci.user.git
   ```
2. To run the application
   ```sh
   .\gradlew bootRun
   ```
3. To run the tests
   ```sh
   .\gradlew test
   ```
4. If you cannot run it locally, you can check the functionality at:
   https://bci-test-user.jriffo.repl.co/swagger-ui.html# <br/>

<p align="right">(<a href="#top">back to top</a>)</p>

## SWAGGER

### GET
http://localhost:8080/swagger-ui.html#/ <br/>

## ENDPOINTS

### GET
`Get All Users` http://localhost:8080//api/user  <br/>

```sh
curl --request GET \
--url http://localhost:8080/api/user \
--header 'Authorization: Bearer TOKEN' \
--cookie JSESSIONID=SESSION_ID
```

### POST
`Authentication: To obtain the token, use the provided user and password` http://localhost:8080/api/login <br/>

```sh
curl --request POST \
--url http://localhost:8080/api/login \
--header 'Content-Type: application/json' \
--cookie JSESSIONID=SESSION_ID \
--data '{
"name": "admin",
"password": "password"
}'
```

`Create User` http://localhost:8080/api/user <br/>

```sh
curl --request POST \
--url http://localhost:8080/api/user \
--header 'Authorization: Bearer TOKEN' \
--header 'Content-Type: application/json' \
--cookie JSESSIONID=SESSION_ID \
--data '{
"email": "email@example.com",
"name": "John Doe",
"password": "password",
"phones": [
{
"citycode": "string",
"contrycode": "string",
"id_phone": 0,
"number": "string"
}
]
}'
```

### DELETE
`Delete User` http://localhost:8080/api/user <br/>

```sh
curl --request DELETE \
  --url http://localhost:8080/api/user/USER_ID \
  --header 'Authorization: Bearer TOKEN' \
  --cookie JSESSIONID=SESSION_ID
```

## Ejemplos

![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

![img_4.png](img_4.png)

![img_5.png](img_5.png)

![img_6.png](img_6.png)
