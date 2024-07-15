# Foro Hub

![Mesa Redonda](foro.jpg)

## Descripción del Proyecto

Foro Hub es una API REST para gestionar un foro donde los usuarios pueden plantear preguntas en tópicos y otros usuarios pueden responder para ayudar a resolver problemas. El proyecto incluye la gestión de Tópicos, Respuestas y Usuarios. Las funcionalidades principales incluyen:

- Implementación de una API con rutas siguiendo las mejores prácticas del modelo REST.
- Validaciones según las reglas de negocio.
- Uso de una base de datos relacional para la persistencia de la información.
- Servicio de autenticación/autorización para restringir el acceso a la información.

## Requisitos

### Programas Necesarios

- **Java JDK**: versión 17 en adelante
- **Maven**: versión 4 en adelante
- **Spring Boot**: versión 3 en adelante
- **MySQL**: versión 8 en adelante
- **IDE**: a elección

### Dependencias Utilizadas

- **Lombok**
- **Spring Web**
- **Spring Boot DevTools**
- **Spring Data JPA**
- **Flyway Migration**
- **MySQL Driver**
- **Validation**
- **Spring Security**

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu-usuario/foro-hub.git
    cd foro-hub
    ```

2. Configura la base de datos en `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
    spring.datasource.username=tu-usuario
    spring.datasource.password=tu-contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Ejecuta las migraciones de Flyway:
    ```sh
    mvn flyway:migrate
    ```

4. Ejecuta la aplicación:
    ```sh
    mvn spring-boot:run
    ```

## Uso

Foro Hub permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) a través de las siguientes rutas:

- **Crear un nuevo tópico**
    ```http
    POST /topicos
    ```

- **Mostrar todos los tópicos creados**
    ```http
    GET /topicos
    ```

- **Mostrar un tópico específico**
    ```http
    GET /topicos/{id}
    ```

- **Actualizar un tópico**
    ```http
    PUT /topicos/
    ```

- **Eliminar un tópico**
    ```http
    DELETE /topicos/{id}
    ```

## Autenticación

El proceso de autenticación en la API se realiza mediante un controller responsable de recibir las solicitudes de inicio de sesión. Utilizamos JWT (JSON Web Token) para compartir información entre cliente y servidor de manera segura.
El uso de tokens añade una capa adicional de seguridad a la aplicación, requiriendo tokens JWT para la autenticación de los usuarios.


---

¡Gracias por utilizar Foro Hub!
