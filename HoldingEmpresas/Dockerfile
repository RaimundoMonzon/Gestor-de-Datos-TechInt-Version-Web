# Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and the pom.xml file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copy the entire src directory
COPY src ./src

# Run the Maven build to create the JAR file
RUN ./mvnw clean package -DskipTests

# Expose port 8090 for the application
EXPOSE 8090

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "target/HoldingEmpresas-0.0.1-SNAPSHOT.jar"]
