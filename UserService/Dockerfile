FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASE=userDb
ENV MONGO_USERNAME=app-root
ENV MONGO_PASSWORD=root123
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
ADD ./target/UserService-0.0.1.jar /usr/src/UserService-0.0.1.jar
ENTRYPOINT ["java", "-jar", "UserService-0.0.1.jar"]