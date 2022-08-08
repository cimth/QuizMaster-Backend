#!/bin/bash

#######
# LOAD ENVIRONMENT VARIABLES FOR DEVELOPMENT TOOLS
#######

echo "Load environment variables ..."

# load SDKMAN settings created in the Dockerfile into the current bash instance
# => only with this command the environment variables for Java and Maven will be used
source ~/.sdkman/bin/sdkman-init.sh

#######
# GO TO PROJECT DIRECTORY
#######

echo "Go to ~/project ..."
cd ~/project

#######
# STARTUP COMMAND
#######

# just keep the container alive; the application has to be started interactively
#echo "Keep container alive ..."
#sleep infinity

# alternative to sleep infinity: automatically build and run application
echo "Start application ..."
mvn spring-boot:run -Dspring-boot.run.profiles=${SPRING_PROFILE}