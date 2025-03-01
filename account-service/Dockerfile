FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY ./account-service/pom.xml ./account-service/

COPY ./account-service/src ./account-service/src

WORKDIR /app/account-service
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/account-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]