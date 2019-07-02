FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASE=reminderDb
ENV MONGO_USERNAME=chandan
ENV MONGO_PASSWORD=root
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
ADD ./target/ReminderService-0.0.1.jar /usr/src/ReminderService-0.0.1.jar
ENTRYPOINT ["java", "-jar", "ReminderService-0.0.1.jar"]
