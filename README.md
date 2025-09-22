## GameBackLog

Aplicación REST backend para gestionar una lista personal de videojuegos (Game Backlog). Implementada con Spring Boot, JPA y JWT para autenticación.

## Descripción

Esta API permite: registrar/ingresar usuarios, buscar videojuegos usando la API externa RAWG, crear/actualizar/eliminar juegos locales y mantener una lista de juegos por usuario.

## Tecnologías

- Java 24
- Spring Boot 3.5.x (Web, Data JPA, Security, WebFlux)
- Maven
- MySQL (conector incluido)
- JWT (jjwt)
- Lombok (para reducir boilerplate)

## Requisitos

- Java 24 instalado y configurado en PATH.
- Maven instalado.
- Base de datos MySQL accesible.
- (Opcional) Una clave de la API RAWG si quieres usar la búsqueda externa.

## Configuración

Copiar `src/main/resources/application-example.properties` a `src/main/resources/application.properties` (o definir variables de entorno) y ajustar las propiedades:

- `spring.datasource.url` — URL de la base de datos MySQL (ej: jdbc:mysql://localhost:3306/gamebacklog)
- `spring.datasource.username` — usuario de la DB
- `spring.datasource.password` — contraseña de la DB
- `rawg.api.key` — (opcional) clave para la API RAWG
- Otras propiedades JPA ya vienen en el ejemplo (`spring.jpa.hibernate.ddl-auto=update`, `show-sql`, etc.)

Ejemplo mínimo (archivo `application.properties`):

```powershell
spring.application.name=GameWebApp
rawg.api.key=TU_RAWG_API_KEY
spring.datasource.url=jdbc:mysql://localhost:3306/gamebacklog
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.servlet.session.cookie.same-site=strict
```

Nota: El proyecto usa `spring.jpa.hibernate.ddl-auto=update` para crear/actualizar tablas automáticamente en desarrollo.

## Endpoints principales

Rutas base definidas por los controladores:

- Usuarios
  - `POST /users/register` — Registrar un nuevo usuario (payload: modelo `User`).
  - `POST /users/login` — Login; devuelve JWT en `AuthResponse`.
  - `GET /users` — Listar usuarios (requiere autenticación).
  - `GET /users/{id}` — Obtener usuario por id.
  - `PUT /users/{id}` — Actualizar usuario.
  - `DELETE /users/{id}` — Eliminar usuario.

- Juegos (recursos locales y búsqueda externa)
  - `GET api/games` — Obtener todos los juegos guardados.
  - `GET api/games/{id}` — Obtener juego por id.
  - `POST api/games` — Crear un nuevo juego (payload: `Game`).
  - `PUT api/games/{id}` — Actualizar juego.
  - `DELETE api/games/{id}` — Eliminar juego.
  - `GET api/games/search?title=...` — Buscar por título usando RAWG (requiere `rawg.api.key`).
  - `GET api/games/search/{id}` — Obtener detalles externos por id (RAWG).

- Lista de usuario (user-games)
  - `GET /api/usergames` — Obtener la lista de juegos del usuario.
  - `POST /api/usergames` — Añadir un juego a la lista (payload: `Game` o referencia según DTO).
  - `DELETE /api/usergames/{id}` — Eliminar de la lista por id.

Observación: La configuración de seguridad permite `POST /users/register` y `POST /users/login` sin token; el resto de rutas requieren JWT.

## Seguridad

- Autenticación basada en JWT. Al hacer login (`/users/login`) se devuelve un token que debe enviarse en peticiones autenticadas.
- El token se firma con una clave interna (ver `JwtUtils`) y expira en 24 horas.
- La configuración CORS por defecto permite orígenes en `http://127.0.0.1:3000` (modificar en `SecurityConfig` si es necesario).

Enviar el token en las peticiones autorizadas con el header:

```
Authorization: Bearer <token>
```

## Cómo ejecutar

Desde la raíz del proyecto:

```powershell
mvn clean package
mvn spring-boot:run
```

O ejecutar el JAR empaquetado:

```powershell
mvn clean package
java -jar target\GameWebApp-0.0.1-SNAPSHOT.jar
```


## Notas de despliegue

- Asegúrate de configurar la base de datos antes de arrancar en producción y de no exponer la clave secreta de JWT en el repositorio.
- Considera cambiar `spring.jpa.hibernate.ddl-auto` a `validate` o eliminarlo en producción.

## Archivos relevantes

- `pom.xml` — dependencias y build
- `src/main/resources/application-example.properties` — ejemplo de configuración
- `src/main/java/halepensis/dev/gamewebapp/controller` — controladores y endpoints
- `src/main/java/halepensis/dev/gamewebapp/security` — JWT y filtros de seguridad

