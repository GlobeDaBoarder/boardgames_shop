# container for client
# alpine linux + install java + tomcat + code running in tomcat +
# MySql + front end all running in the same container
# We expect one command to run everything

FROM ubuntu:latest
LABEL authors="glebi"

ENTRYPOINT ["top", "-b"]
