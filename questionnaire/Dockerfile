# Use a Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a smaller JDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Set environment variables for database connection
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV SPRING_KAFKA_BOOTSTRAP_SERVERS=${SPRING_KAFKA_BOOTSTRAP_SERVERS}

# Create directory for logs
RUN mkdir -p logs

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
