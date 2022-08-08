#!/bin/bash

#######
# CHOWN MOUNTED FILES
#######

echo "Run 'chown' on mounted files ..."
sudo chown -R $USER:$USER $USER_HOME/project
sudo chown -R $USER:$USER $USER_HOME/.m2

#######
# RUN MOUNTED PROJECT SCRIPT
#######

# the project script is not used as entrypoint script to avoid rebuilding the container each time the project
# script is updated
echo "Run project script ..."
/bin/bash -c ~/run_project.sh
