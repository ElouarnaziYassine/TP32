# Étape 1 : Build
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY ./pom.xml ./
COPY ./src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]