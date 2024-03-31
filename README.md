# Ejercicio Desafio BCI


## Requerimientos.

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error.
Todos los mensajes deben seguir el formato:
{"mensaje": "mensaje de error"}

## Registro

- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más
un listado de objetos "teléfono", respetando el siguiente formato:

        {
        "name": "Ricardo Lucero",
        "email": "ricardo@lucero.cl",
        "password": "miclavesegura123",
        "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "56"
        }
        ]
        }

Mediante postman con el metodo post realizar la llamada a la siguiente url:
http://localhost:8080/v1/desafioBci/doSignIn


- Responder el código de status HTTP adecuado
- En caso de éxito, retorne el usuario y los siguientes campos:
- id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más
deseable un UUID)
- created: fecha de creación del usuario
- modified: fecha de la última actualización de usuario
- last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha
de creación)
- token: token de acceso de la API (puede ser UUID o JWT)
- isactive: Indica si el usuario sigue habilitado dentro del sistema.
- Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya
registrado".
- El correo debe seguir una expresión regular para validar que formato sea el correcto.
(aaaaaaa@dominio.cl)
- La clave debe seguir una expresión regular para validar que formato sea el correcto. (Una
Mayuscula, letras minúsculas, y dos números)
- Se debe hacer traza de logs dentro del aplicativo.
- El token deberá ser persistido junto con el usuario


## Requisitos:


- Banco de datos en memoria. Ejemplo: HSQLDB o H2.
- Proceso de build vía Gradle o Maven.
- Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA.
- Framework SpringBoot.
- Java 8+
- Entrega en un repositorio público (github o bitbucket) con el código fuente y script de
creación de BD.
- Readme explicando cómo probarlo.
- Diagrama de la solución.

  




### Dependencias


| Dependencia | Version |
| ------ | ------ |
| IntelliJ IDEA | 1.8.0_152 |
| Gradle | 4.10.3|
| H2 | 2.4.1 |
| Java JDK  | 1.8 |
| Postman  | 7.18.0 |
| Windows | 10 |


### Autor

 - Ricardo Lucero

