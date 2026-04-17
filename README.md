# API sobre Tareas 1.0

API REST desarrollada con Spring Boot para la gestión de tareas personales con persistencia en MySQL y seguridad integrada.

## Requisitos Previos

- Java 23 o superior
- Maven 3.9 o superior
- Docker (opcional, para la base de datos)

## Configuración de la Base de Datos

Si utilizas Docker, puedes iniciar una instancia de MySQL compatible con la configuración por defecto mediante el siguiente comando:

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
```
El archivo de configuración src/main/resources/application.properties debe contener los siguientes parámetros para asegurar la conexión y la creación automática del esquema:
```bash
spring.application.name=todo-rest
```
# Configuración de conexión
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?createDatabaseIfNotExist=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```
# Configuración de JPA e Hibernate
```bash
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
# Instalación e Inicio
- Descargue o clone el código fuente del proyecto.
- Navegue hasta la carpeta raíz donde se encuentra el archivo pom.xml.

Ejecute la compilación y limpieza del proyecto:
```bash
mvn clean install
```
Inicie la aplicación:
```bash
mvn spring-boot:run
```
La aplicación estará operativa en:
- http://localhost:8080

## Funcionamiento y Seguridad

La API utiliza Spring Security. Por defecto, la mayoría de las rutas están protegidas.

Registro: El usuario debe registrarse primero en el endpoint de autenticación.
Autenticación: Se utiliza Basic Auth para las peticiones protegidas.
Propiedad: El sistema asegura que los usuarios solo puedan interactuar con las tareas que ellos mismos han creado.
Listado de Endpoints
Autenticación
POST /auth/register
Crea un nuevo usuario. Requiere un cuerpo JSON con username, email y password.
Tareas (Requieren Autenticación)
GET /task/
Recupera todas las tareas del usuario autenticado.
POST /task/
Crea una nueva tarea vinculada al usuario.
GET /task/{id}
Obtiene el detalle de una tarea específica.
PUT /task/{id}
Actualiza una tarea existente del usuario.
DELETE /task/{id}
Elimina una tarea del sistema.
Documentación Interactiva (Swagger)

# La documentación completa de los modelos y las pruebas de los endpoints se pueden consultar en:

Interfaz de usuario:
http://localhost:8080/swagger-ui/index.html
Documentación técnica (JSON):
http://localhost:8080/v3/api-docs
