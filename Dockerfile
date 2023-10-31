FROM openjdk:17-jdk-slim
ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/deve/./urandom "

EXPOSE 8080

# WORKDIR /app
COPY ./build/libs/my-app-1.0.0.jar /usr/app/
WORKDIR /usr/app

# COPY ./build/libs/my-app-1.0-SNAPSHOT.jar /usr/app/


#Passing a command line to run
ENTRYPOINT ["java", "-jar", "my-app-1.0.0.jar"]

