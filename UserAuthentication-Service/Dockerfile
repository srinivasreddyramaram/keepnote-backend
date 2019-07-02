FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=userDb
ENV MYSQL_USER=app-root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_HOST=localhost
ADD ./target/UserAuthentication-Service-0.0.1.jar /usr/src/UserAuthentication-Service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "UserAuthentication-Service-0.0.1.jar"]
