FROM maven:4.0.0-openjdk-17 AS build
WORKDIR /app
COPY /src /src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /src /src
COPY --from=build /app/target/app.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
