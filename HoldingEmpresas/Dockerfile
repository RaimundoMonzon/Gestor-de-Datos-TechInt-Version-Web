# Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and the pom.xml file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Add executable permissions to mvnw
RUN chmod +x ./mvnw

# Copy the entire src directory
COPY src ./src

# Run the Maven build to create the JAR file
RUN ./mvnw clean package -DskipTests

# Expose port 8090 for the application
EXPOSE 8090

# Set environment variables for AWS MySQL connection
ENV SPRING_DATASOURCE_URL="jdbc:mysql://holding-db.cb6egcecy5xf.us-east-2.rds.amazonaws.com:3306/holdingDataBase"
ENV SPRING_DATASOURCE_USERNAME="rootRai"
ENV SPRING_DATASOURCE_PASSWORD="~v#N]1&2&4N4WZrDagZ5Z9u,^it[V$"
# Set enviroment variables for Local MySQL connection
# ENV SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/holdingDataBase"
# ENV SPRING_DATASOURCE_USERNAME="rootRai"
# ENV SPRING_DATASOURCE_PASSWORD="root"

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "target/HoldingEmpresas-0.0.1-SNAPSHOT.jar"]