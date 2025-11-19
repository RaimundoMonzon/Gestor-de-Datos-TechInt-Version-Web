# Use OpenJDK 21 base image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Give permission to mvnw
RUN chmod +x ./mvnw

# Copy the source code
COPY src ./src

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app actually uses
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "target/HoldingEmpresas-0.0.1-SNAPSHOT.jar"]
