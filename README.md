# cl.bci.user
Prueba Postulación BCI


### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Get a free API Key at [https://example.com](https://example.com)
2. Clone the repo
   ```sh
   git clone https://github.com/your_username_/Project-Name.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API in `config.js`
   ```js
   const API_KEY = 'ENTER YOUR API';
   ```

<p align="right">(<a href="#top">back to top</a>)</p>

Para ejecutar la aplicación: gradlew bootRun

Para correr los test: gradlew test

Para revisar la documentación de la API por favor ingresar al link: http://localhost:8080/swagger-ui.html#/

El end point que crea usuario y entrega el token para consumir el resto de la API es localhost:8080/api/login

Además indicar que para consumir la API se debe enviar el header Authorization con valor Bearer + token entregado por end point login
