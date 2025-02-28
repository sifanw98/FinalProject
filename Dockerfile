# 1. Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# 2. Set the working directory in the container
WORKDIR /app

# 3. Copy the jar file into the container
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service.jar

# 5. Command to run the jar
ENTRYPOINT ["java","-jar","/app/account-service.jar"]
