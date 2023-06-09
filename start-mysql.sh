#!/bin/bash

# Start MySQL
service mysql start
sleep 10

# Setup MySQL
mysql -u root -p"root" -e "CREATE USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root'; GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'; FLUSH PRIVILEGES;"
mysql -u root -p"root" -e "CREATE DATABASE shop;"

# Start Tomcat
/usr/share/tomcat9/bin/catalina.sh run


