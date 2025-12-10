# Dockerfile for CustomerInformationSystem Spring Boot application
# Uses Eclipse Temurin 17 JRE as base
FROM eclipse-temurin:17-jre

ARG JAR_FILE=target/ docker images    -1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
