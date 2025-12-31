# Étape 1 : Construction (Builder)
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY ./pom.xml ./
COPY ./src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image finale (Runtime)
# Remplacement de openjdk:17-jdk-alpine par eclipse-temurin:17-jdk-alpine
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]