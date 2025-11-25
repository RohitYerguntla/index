# -----------------------------------------
# 1) Build Stage (uses Maven + Java)
# -----------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build project (creates JAR inside target/)
RUN mvn clean package -DskipTests


# -----------------------------------------
# 2) Run Stage (uses lightweight JDK)
# -----------------------------------------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Start application
ENTRYPOINT ["java", "-jar", "app.jar"]
