FROM openjdk:17 as build
WORKDIR /app
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
