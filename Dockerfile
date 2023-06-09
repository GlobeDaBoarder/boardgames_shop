## container for client
## ubuntu core + install java + tomcat + code running in tomcat +
## MySql + front end all running in the same container
## We expect one command to run everything
#
# Start from Ubuntu
FROM ubuntu:latest

# Update apt
RUN apt-get update

# Install MySQL
RUN echo "mysql-server mysql-server/root_password password root" | debconf-set-selections
RUN echo "mysql-server mysql-server/root_password_again password root" | debconf-set-selections
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y mysql-server

# Install OpenJDK 17 JRE
RUN apt-get install -y openjdk-17-jre

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64/

# Make JAVA_HOME available to other processes
RUN echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/' >> ~/.bashrc

# Install Tomcat
RUN apt-get install -y tomcat9 tomcat9-admin

# Create a symbolic link for Tomcat's configuration files
RUN ln -s /etc/tomcat9 /usr/share/tomcat9/conf


# Expose the MySQL and Tomcat ports
EXPOSE 3306 8080

# Copy the startup script into the image and set permissions
COPY start-mysql.sh /start-mysql.sh
RUN chmod 0755 /start-mysql.sh

# Start MySQL and Tomcat when the container starts
CMD ["/bin/bash", "/start-mysql.sh"]







