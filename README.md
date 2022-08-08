Backend of QuizMaster
=====================

With QuizMaster you can create and play quizzes where you have to select 1 of 4 answers.

This project contains the backend of QuizMaster. 
The backend handles the management of your data like predefined quizzes and the question pool inside an H2 file-based 
database. 
Thus, the backend provides endpoints for the typical CRUD operations on quizzes and questions.

For making the frontend easier to create, you can also get a single question in a special play format where the answers
are shuffled and assigned to an answer letter A, B, C or D.


## API description and frontend

You can find the API description, created with OpenAPI / Swagger, inside the following repository:
https://github.com/cimth/quizmaster_api

The frontend, created with Angular, is located within the following repository:
https://github.com/cimth/quizmaster_frontend


## How to run the QuizMaster backend in Docker

You can use the Docker setup provided inside the [docker](docker) directory.
Without changing this setup, the application is started with the following settings:

| Usage                     | Default value         | Notes                                                                                            |
|---------------------------|-----------------------|--------------------------------------------------------------------------------------------------|
| Spring profile            | local                 | The configuration [application-local.yml](src/main/resources/application-local.yml) will be used |
| User for the database     | dbuser                |                                                                                                  |
| Password for the database | dbpassword            |                                                                                                  |
| Port of the backend       | 8080                  |                                                                                                  |
| URL of the frontend       | http://localhost:4200 | Used to allow frontend requests in the CORS settings                                             |

You can run the Docker setup via `docker-compose up` inside the [docker](docker) directory.
The setup will automatically install all dependencies and run the application.

**Note:**
You might have to adjust the permissions to the project's directory depending on your system or add `chown` commands 
(or similar) to have correct access privileges to the files.

## How to run the QuizMaster backend as Maven goal

1. Provide an HTTPS configuration (for details look up the ['HTTPS' section](#https))
2. Provide a database configuration (for details look up the ['Database' section](#database))
3. Run the Maven goal `spring-boot:run`

   ```
   mvn spring-boot:run -Dspring-boot.run.profiles=${SPRING_PROFILE}
   ```

## How to run the QuizMaster backend as a JAR

1. Provide an HTTPS configuration (for details look up the ['HTTPS' section](#https))
2. Provide a database configuration (for details look up the ['Database' section](#database))
3. Execute Maven build lifecycle "package"

   ```
   mvn package -Dspring.profiles.active=${SPRING_PROFILE}
   ```

4. Start the created JAR with `java -jar <JAR file>` (optionally add `-Dspring.profiles.active=<Spring profile>`) 
   for using a specific profile)

   ```
   java -Dspring.profiles.active=${SPRING_PROFILE} -jar /project/target/quizmaster_backend-${APP_VERSION_FROM_POM_XML}-spring-boot.jar
   ```

You might wish to run the QuizMaster backend in your favorite IDE. 
To do this, skip the steps 3 and 4 and run the application just with your default IDE workflow.


## Admin Token and HTTPS

### Admin Token

For modifying the database, i.e. creating, deleting or updating questions or predefined quizzes, you have to provide
an Admin Token in the Authorization header of your request. 
This token is re-created on each startup of the application and will be printed on the console. 
Thus, only the admin of the server can modify the database.

For creating the Admin Token the class `java.security.SecureRandom` is used along with `java.util.Base64` for providing
a secure token. 
The token has a length of 512 bits (64 bytes) and will provide good security for avoiding someone to mess around with 
your data. 

### HTTPS

#### Only use HTTP

By setting `server/ssl/enabled` to `false` you have not to provide any HTTPS configurations but can use HTTP instead.

#### Configuring HTTPS

To avoid someone eavesdropping the Admin Token or other data you send to the server, the QuizMaster backend is prepared
to use HTTPS-only connections. 

However, you have to set up your HTTPS configuration manually by filling the related data inside your 
[application.yml](src/main/resources/application.yml) file. You can also use a personal .yml file to configure HTTPS 
in a separate profile.

If you only want to use a self-signed certificate (which should only be used on localhost for private purposes!), you
can e.g. create a self-signed .p12 keystore as follows:

```
keytool -genkeypair -alias local_quizmaster -keyalg RSA -keysize 8192 -storetype PKCS12 -keystore local_quizmaster.p12 -validity 3650
```

Assuming you are storing this .p12 file inside the [resources](src/main/resources) directory in a separate directory
called *certificate*, you could use the following configuration in your .yml file:

```yaml
server:
  ssl:
    enabled: true
     
    # set up the certificate
    key-store: classpath:certificate/local_quizmaster.p12
    key-store-password: <your keystore PW>
    key-store-type: pkcs12
    key-alias: local_quizmaster
    key-password: <your key PW>
```

Anything else will be done by Spring and the server configurations already inserted into the QuizMaster backend.


## CORS

You have to enable CORS for the host running the frontend. 
If you e.g. run the frontend on `http://localhost:4200`, you have to allow this host in your CORS configuration.

You can provide the frontend host and port via the environment variable `FRONTEND_URL`, e.g. by executing the command 
`export FRONTEND_URL="http://localhost:4200"` and then add (or adjust) the following settings in your 
[application.yml](src/main/resources/application.yml) file:

```yaml
frontend-cors:
  enabled: true
  url: ${FRONTEND_URL}
```

The configuration file [application-local.yml](src/main/resources/application-local.yml) for the prepared profile 
`local` does already contain this setup so that you can just populate the `FRONTEND_URL` environment variable.

If you want to make further adjustments to the CORS settings, please consider the class 
[WebSecurityConfig](src/main/java/com/example/quizmaster_backend/configuration/WebSecurityConfig.java).


## Database

The current setup of the QuizMaster backend uses a file-based H2 database. 
You have to provide a database file by your own or let it automatically be created by Spring. 
To use/create a database you have to adjust the following configuration inside your 
[application.yml](src/main/resources/application.yml) file or a personal .yml profile:

```
spring:
  datasource:
    url: jdbc:h2:<path to db-file>
    username: <db-user>
    password: <db-pw>
```

Assuming you want to use an H2 database file called `quizmaster_db` (without the file extension!) inside a `database` 
directory placed in the project's root directory, you could use the following configuration:

```
spring:
  datasource:
    url: jdbc:h2:./database/quizmaster_db
    username: <db-user>
    password: <db-password>
```

If you want to populate the username and password by environment variables, you can use the following setup:

```
spring:
  datasource:
    url: jdbc:h2:./database/quizmaster_db
    username: ${BACKEND_DB_USER}
    password: ${BACKEND_DB_PASSWORD}
```

With this, you can just create environment variables `BACKEND_DB_USER` and `BACKEND_DB_PASSWORD` which then will be 
used by Spring.
An example of the usage is provided in the project's Docker setup.

When you use the JAR file for starting the backend, the configuration above expects the *database* directory to be inside
the same directory as your JAR file.

Of course, you can use whatever database you prefer and adjust the configuration to your personal preferences.