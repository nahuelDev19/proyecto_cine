# Proyecto Cine API

## Descripción

Este proyecto es una API para la gestión de un cine. Permite manejar películas, funciones, reservas y usuarios. Está construido con Spring Boot y utiliza una base de datos relacional para almacenar la información.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para el desarrollo de aplicaciones Java.
- **Java**: Lenguaje de programación.
- **JPA/Hibernate**: Persistencia de datos.
- **MySQL**: Sistema de gestión de bases de datos.
- **Lombok**: Biblioteca para simplificar el código Java.
- **Jakarta Validation**: Para validaciones de entrada.

## Estructura del Proyecto

- **Modelos**: Clases que representan las entidades del sistema y las solicitudes/respuestas de la API.
- **Repositorios**: Interfaces para acceder a la base de datos.
- **Servicios**: Lógica de negocio y operaciones sobre los datos.
- **Controladores**: Endpoints de la API para interactuar con el sistema.

## Endpoints de la API

### Películas

- **GET /peliculas**: Obtiene una lista de películas paginadas y opcionalmente filtradas por género.
- **POST /peliculas**: Crea una nueva película.
- **GET /peliculas/{id}**: Obtiene los detalles de una película por su ID.
- **PUT /peliculas/{id}**: Actualiza los detalles de una película.
- **DELETE /peliculas/{id}**: Elimina una película.

### Funciones

- **GET /funciones**: Obtiene una lista de funciones.
- **POST /funciones**: Crea una nueva función.
- **GET /funciones/{id}**: Obtiene los detalles de una función por su ID.
- **PUT /funciones/{id}**: Actualiza los detalles de una función.
- **DELETE /funciones/{id}**: Elimina una función.

### Reservas

- **GET /reservas**: Obtiene una lista de reservas.
- **POST /reservas**: Crea una nueva reserva.
- **GET /reservas/{id}**: Obtiene los detalles de una reserva por su ID.
- **PUT /reservas/{id}**: Actualiza los detalles de una reserva.
- **DELETE /reservas/{id}**: Elimina una reserva.

### Usuarios

- **GET /usuarios**: Obtiene una lista de usuarios.
- **POST /usuarios**: Crea un nuevo usuario.
- **GET /usuarios/{id}**: Obtiene los detalles de un usuario por su ID.
- **PUT /usuarios/{id}**: Actualiza los detalles de un usuario.
- **DELETE /usuarios/{id}**: Elimina un usuario.
