FROM openjdk:17-jdk-slim
#ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/deve/./urandom "

#EXPOSE 8086

# WORKDIR /app
WORKDIR /app
COPY /target/stockmanagement-eventstore-1.0.0.jar /app/my-app.jar


# COPY ./build/libs/my-app-1.0-SNAPSHOT.jar /usr/app/


#Passing a command line to run
ENTRYPOINT ["java", "-jar", "my-app.jar"]

