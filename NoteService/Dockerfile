FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASE=noteDb
ENV MONGO_USERNAME=chandan
ENV MONGO_PASSWORD=root
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
ADD ./target/NoteService-0.0.1.jar /usr/src/NoteService-0.0.1.jar
ENTRYPOINT ["java", "-jar", "NoteService-0.0.1.jar"]
