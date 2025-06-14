FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY target/project_tracker-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
