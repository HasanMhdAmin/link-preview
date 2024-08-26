# Use a base image with JDK
FROM gradle:7.6.0-jdk17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the working directory
COPY --chown=gradle:gradle . .

# Build the project using Gradle (you can replace 'build' with your desired Gradle task)
RUN gradle build --no-daemon

# Second stage to create the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build stage to the current stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port on which your Spring Boot app will run
EXPOSE 8080

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]