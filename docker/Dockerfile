################################################################################

#####################
# BASIC UBUNTU IMAGE WITH NON-ROOT USER
#####################
FROM ubuntu:latest AS non-root
SHELL ["/bin/bash", "-c"]

# get user data from docker-compose.yml
ENV USER="docker"
ARG USER_PASSWORD

# no interactive installation
ENV DEBIAN_FRONTEND=noninteractive

# add user
RUN adduser --disabled-password $USER && \
    echo "$USER":"$USER_PASSWORD" | chpasswd

# run as non-root user in the following
USER $USER
WORKDIR /home/$USER

################################################################################

#####################
# JAVA WITH MAVEN IMAGE
#####################
FROM non-root AS java-with-maven

# build args
ARG JAVA_VERSION="17.0.4-tem"
ARG MAVEN_VERSION="3.8.6"

#######
# INSTALL JAVA AND MAVEN VIA SDKMAN
# See: https://e.printstacktrace.blog/using-sdkman-as-a-docker-image-for-jenkins-pipeline-a-step-by-step-guide/
#######

# install prerequisites
USER root

RUN apt-get update && \
    apt-get install -y zip unzip curl

# install SDKMAN, Java and Maven for the non-root user
USER $USER

RUN curl -s url -s "https://get.sdkman.io" | bash && \
    source ~/.sdkman/bin/sdkman-init.sh && \
    sdk install java $JAVA_VERSION && \
    sdk install maven $MAVEN_VERSION && \
    rm -rf ~/.sdkman/archives/* && \
    rm -rf ~/.sdkman/tmp/*

################################################################################

#####################
# ENTRYPOINT IMAGE
#####################

FROM java-with-maven AS entrypoint

# set container user and starting directory when entering the container interactively
USER $USER
WORKDIR /home/$USER/project

# run the mounted entrypoint script
ENTRYPOINT ["/bin/bash", "-c", "/home/$USER/entrypoint.sh"]