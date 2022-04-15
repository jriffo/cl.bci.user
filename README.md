# cl.bci.user
Prueba Postulación BCI


### Installation

1. Clonar el repo
   ```sh
   git clone https://github.com/jriffotoro/cl.bci.user.git
   ```
2. Para ejecutar la aplicación
   ```sh
   .\gradlew bootRun
   ```
3. Para correr los test
   ```sh
   .\gradlew test
   ```
4. Si no puede ejecutar de manera local puede verificar el funcionamiento en:
   https://bci-test-user.jriffo.repl.co/swagger-ui.html# <br/>

<p align="right">(<a href="#top">back to top</a>)</p>

## SWAGGER

### GET
http://localhost:8080/swagger-ui.html#/ <br/>


## END POINTS

### GET
`Get All Users` http://localhost:8080//api/user  <br/>

```sh
curl --request GET \
--url http://localhost:8080/api/user \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjUwMDQ5NDMwLCJleHAiOjE2NTAwNTAwMzB9.EiFp1gXCWqC_691w_TdkfK1CpVY-93wM2_iOkyW90MFq5K0PyfZ5ua9Ft09ukwiNm-bCkFEdo235FJSc4XwTyA' \
--cookie JSESSIONID=939C0D726F4CF007762D564167C79E27
  ```

### POST
`Autentication: Para obtener el token se debe usar el user y password que se indica` http://localhost:8080/api/login <br/>

```sh
   curl --request POST \
--url http://localhost:8080/api/login \
--header 'Content-Type: application/json' \
--cookie JSESSIONID=939C0D726F4CF007762D564167C79E27 \
--data '{
"name": "admin",
"password": "Bgfdgfdg23dsfds"
}'
   ```

`Create User` http://localhost:8080/api/user <br/>

```sh
curl --request POST \
--url http://localhost:8080/api/user \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjUwMDQ5NDMwLCJleHAiOjE2NTAwNTAwMzB9.EiFp1gXCWqC_691w_TdkfK1CpVY-93wM2_iOkyW90MFq5K0PyfZ5ua9Ft09ukwiNm-bCkFEdo235FJSc4XwTyA' \
--header 'Content-Type: application/json' \
--cookie JSESSIONID=939C0D726F4CF007762D564167C79E27 \
--data '{

"email": "jriffo.ttoro@gmail.com",
"name": "José Riffo",
"password": "Bgfdgfdg23dsfds",
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
  --url http://localhost:8080/api/user/81fc694c-b296-4f22-91d7-a7921b450db3 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjUwMDQ5NDMwLCJleHAiOjE2NTAwNTAwMzB9.EiFp1gXCWqC_691w_TdkfK1CpVY-93wM2_iOkyW90MFq5K0PyfZ5ua9Ft09ukwiNm-bCkFEdo235FJSc4XwTyA' \
  --cookie JSESSIONID=939C0D726F4CF007762D564167C79E27
```

## Ejemplos

![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

![img_4.png](img_4.png)

![img_5.png](img_5.png)

![img_6.png](img_6.png)