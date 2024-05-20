FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the build artifacts from the Gradle build
COPY build/libs/jenkins-testing-0.0.1-SNAPSHOT.jar testing.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]