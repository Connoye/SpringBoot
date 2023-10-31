FROM openjdk:17-jdk-slim


EXPOSE 8080

WORKDIR /usr/app
COPY /artifacts/stockmanagement_eventstore-1.0.0.jar /usr/app/

# COPY ./build/libs/my-app-1.0-SNAPSHOT.jar /usr/app/


ENTRYPOINT ["java", "-jar", "stockmanagement_eventstore-1.0.0.jar"]
