# Backend of QuizMaster

With QuizMaster you are able to create and play quizzes with 10 to 30 questions, providing a custom question pool.

This project contains the backend of QuizMaster. The backend handles the management of your data like predefined 
quizzes and the question pool inside an H2 file-based database. Thus, the backend provides endpoints for the typical 
CRUD operations on quizzes and questions.

For making the frontend easier to create, you can also get a single question in a special play format where the answers
are shuffled and assigned to a answer letter A, B, C or D.

## API description and frontend

You can find the API description, created with OpenAPI / Swagger, inside the following repository:
https://github.com/cimth/quizmaster_api

The frontend is currently in work. After finishing a stable version with all necessary features, a URL to the 
corresponding repository will be inserted here.

## How to run the QuizMaster backend as a JAR

1. Install the Maven dependencies
2. Provide an HTTPS configuration (for details look up the ['HTTPS' section](#https))
3. Provide a database configuration (for details look up the ['Database' section](#database))
4. Execute Maven build lifecycle "package"
5. Start the created JAR with `java -jar <JAR file>` (optionally add `-Dspring.profiles.active=<Spring profile>`) 
   for using a specific profile)

You might wish to run the QuizMaster backend in your favorite IDE. To do this, skip the steps 4 and 5 and run the
application just with your default IDE workflow.

## Admin Token and HTTPS

### Admin Token

For modifying the database, i.e. creating, deleting or updating questions or predefined quizzes, you have to provide
an Admin Token in the Authorization header of your request. This token is re-created on each startup of the application 
and will be printed on the console. Thus, only the admin of the server can modify the database.

For creating the Admin Token the class `java.security.SecureRandom` is used along with `java.util.Base64` for providing
a secure token. The token has a length of 512 bits (64 bytes) and will provide good security for avoiding someone to
mess around with your data. 

### HTTPS

To avoid someone eavesdropping the Admin Token or other data you send to the server, the QuizMaster backend is prepared
to use HTTPS-only connections. However, you have to set up your HTTPS configuration manually by filling the related
data inside your [application.yml](src/main/resources/application.yml) file. You can also use a personal .yml file to
configure HTTPS in a separate profile.

If you only want to use a self-signed certificate (which should only be used on localhost for private purposes!), you
can e.g. create a self-signed .p12 keystore as follows:

```
keytool -genkeypair -alias local_quizmaster -keyalg RSA -keysize 8192 -storetype PKCS12 -keystore local_quizmaster.p12 -validity 3650
```

Assuming you are storing this .p12 file inside the [resources](src/main/resources) directory in a separate directory
called *certificate*, you could use the following configuration in your .yml file:

```
key-store: classpath:certificate/local_quizmaster.p12
key-store-password: <your keystore PW>
key-store-type: pkcs12
key-alias: local_quizmaster
key-password: <your key PW>
```

Anything else will be done by Spring and the server configurations already inserted into the QuizMaster backend.

## CORS

If running the frontend on localhost:4200, you have to enable CORS for this host. The easiest way to achieve this is to 
add the line `@CrossOrigin("http://localhost:4200")` above each of the controllers. Because the frontend to be linked 
above will be created with Angular and it does use the Angular default port 4200, this line is already inserted into the 
controller classes. However, if you want to run the frontend on another port or URL, you have to change this line due to 
your actual environment configuration.

## Database

The current setup of the QuizMaster backend uses a file-based H2 database. You have to provide a database file by your
own or let it automatically be created by Spring. To use/create a database you have to adjust the following 
configuration inside your [application.yml](src/main/resources/application.yml) file or a personal .yml profile:

```
spring:
  datasource:
    url: jdbc:h2:<path to db-file>
    username: <db-user>
    password: <db-pw>
```

Assuming you want to use a H2 database file called *quizmaster_db* (without the file extension!) inside a *data* 
directory placed in the project's root directory, you could use the following configuration:

```
spring:
  datasource:
    url: jdbc:h2:./data/quizmaster_db
    username: <db-user>
    password: <db-password>
```

When you use the JAR file for starting the backend, the configuration above expects the *data* directory to be inside
the same directory as your JAR file.

Of course, you can use whatever database you prefer and adjust the configuration to your personal preferences.