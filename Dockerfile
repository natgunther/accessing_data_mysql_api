# Use an official OpenJDK runtime as a parent image
#Stage 1 build
FROM gradle:8.8-jdk17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files and source code into the container
COPY gradle/ gradle/
COPY gradlew .
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the application
RUN gradle build --no-daemon

#Stage 2 build
FROM openjdk:17-jdk-slim

#Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY --from=build /app/build/libs/accessing-data-mysql-0.0.1-SNAPSHOT.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]



