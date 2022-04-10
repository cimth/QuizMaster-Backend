#!/bin/bash

# build project
cd /project
mvn package -Dspring.profiles.active=${SPRING_PROFILE}

# start project
java -Dspring.profiles.active=${SPRING_PROFILE} -jar /project/target/quizmaster_backend-${APP_VERSION_FROM_POM_XML}-spring-boot.jar