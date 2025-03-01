FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY service-registry/pom.xml service-registry/
COPY service-registry/src service-registry/src/

WORKDIR /app/service-registry
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/service-registry/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]