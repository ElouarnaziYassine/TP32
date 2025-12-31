# Étape 1 : Build
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Runtime
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
# Correction de la source du COPY pour pointer vers l'étape builder
COPY --from=builder /app/target/*.jar client-service.jar
ENTRYPOINT ["java","-jar","client-service.jar"]