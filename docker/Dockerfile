FROM maven:3.8.5-openjdk-17

# make run script to entry point to have access on the latest project files as volume
COPY ./run_project.sh /run_project.sh
RUN chmod u+x /run_project.sh
ENTRYPOINT ["/run_project.sh"]