# Student Management System

## Overview

The Student Management System is a robust backend application built with Spring Boot that provides REST APIs for managing student information. The system includes user authentication, role-based access control, and comprehensive CRUD operations for student records.

## Features

- **User Authentication**: Secure JWT-based authentication system
- **Role-Based Authorization**: Different access levels (User, Moderator, Admin)
- **Student Management**: Complete CRUD operations for student records
- **Exception Handling**: Global exception handling with appropriate error responses
- **MongoDB Integration**: NoSQL database for flexible data storage
- **CI/CD Pipeline**: Automated testing, building, and deployment
- **Docker Support**: Containerized deployment for consistency across environments
- **Security Scanning**: Vulnerability scanning for code and container

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security with JWT
- MongoDB
- Docker
- GitHub Actions (CI/CD)
- Trivy (Security Scanning)

---

## Dockerfile Explanation

The project uses a multi-stage Docker build for optimal image size and security:

```dockerfile
# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Production stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create a non-root user to run the application
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Define environment variables
ENV MONGODB_URI=""
ENV JWT_SECRET=""

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```


**Key Features:**
1. **Multi-stage build**: Separates build and runtime environments to create smaller final images
2. **Alpine-based images**: Uses lightweight base images for faster downloads and smaller footprint
3. **Non-root user**: Runs the application as a non-privileged user for enhanced security
4. **Environment variables**: Configures the application through environment variables
5. **Optimized for Spring Boot**: Properly sets up the Java environment for Spring Boot applications

---

## Project Structure

```
spring-boot-ec2-deployment/
├── .github/
│   └── workflows/
│       ├── dockerhub-ec2-cicd.yml
│       ├── ecr-ec2-cicd.yml
│       └── unit-tests-and-vulnerability-scans.yml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── student_managemt_system/
│   │   │               ├── controller/
│   │   │               │   ├── AuthController
│   │   │               │   ├── StudentController
│   │   │               │   └── TestController
│   │   │               ├── exception/
│   │   │               ├── model/
│   │   │               ├── payload/
│   │   │               ├── repository/
│   │   │               ├── security/
│   │   │               └── service/
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── student_managemt_system/
│                       ├── service/
│                       │    └──impl/
│                       └── StudentManagementSystemApplicationTests
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- Docker
- MongoDB instance (local or cloud-based)

---

## Environment Variables

### Application Variables
- `MONGODB_URI`: MongoDB connection string
- `JWT_SECRET`: Secret key for JWT token generation

### CI/CD Deployment Variables

**For Both Workflows:**
- `SSH_USER`: Username for SSH access to EC2 instance
- `SSH_HOST`: Hostname or IP address of the EC2 instance
- `SSH_PRIVATE_KEY`: Private SSH key for authentication to EC2 instance

**For DockerHub Workflow:**
- `DOCKER_USERNAME`: DockerHub username for image publishing
- `DOCKER_PASSWORD`: DockerHub password or access token

**For AWS ECR Workflow:**
- `AWS_ACCESS_KEY_ID`: AWS access key with ECR permissions
- `AWS_SECRET_ACCESS_KEY`: AWS secret key with ECR permissions
- `AWS_REGION`: AWS region where ECR repository is located
- `ECR_REGISTRY`: AWS ECR registry URL
- `ECR_REPOSITORY_URI`: Full URI of the ECR repository

---

## Running Locally

### Without Docker

1. Clone the repository:
   ```bash
   git clone https://github.com/PasinduWaidyarathna/spring-boot-ec2-deployment.git
   cd spring-boot-ec2-deployment
   ```

2. Set environment variables:
   ```bash
   export MONGODB_URI=your_mongodb_connection_string
   export JWT_SECRET=your_jwt_secret
   ```

3. Build and run the application:
   ```bash
   mvn clean install
   java -jar target/*.jar
   ```

---

### With Docker

1. Clone the repository:
   ```bash
   git clone https://github.com/PasinduWaidyarathna/spring-boot-ec2-deployment.git
   cd spring-boot-ec2-deployment
   ```

2. Build the Docker image:
   ```bash
   docker build -t student-management-backend:latest .
   ```

3. Run the container:
   ```bash
   docker run -d -p 8080:8080 \
     -e MONGODB_URI=your_mongodb_connection_string \
     -e JWT_SECRET=your_jwt_secret \
     --name student-management-backend \
     student-management-backend:latest
   ```

---

## API Endpoints

### Authentication

- `POST /api/auth/signup` - Register a new user
- `POST /api/auth/signin` - Login and receive JWT token

### Student Management

- `GET /api/v1/students` - Get all students
- `GET /api/v1/students/{id}` - Get a specific student
- `POST /api/v1/students` - Create a new student
- `PUT /api/v1/students/{id}` - Update a student
- `DELETE /api/v1/students/{id}` - Delete a student

---

## CI/CD Pipeline

The project includes three GitHub Action workflows:

### 1. DockerHub and EC2 Deployment (`dockerhub-ec2-cicd.yml`)

This workflow builds, tests, and deploys the application to an EC2 instance using Docker images stored in DockerHub.

**Workflow Steps:**
1. **Test**: Builds the application and runs tests
2. **Docker**: Builds a Docker image and pushes it to DockerHub
3. **EC2**: Connects to the EC2 instance, pulls the latest Docker image, and deploys it

---

### 2. Amazon ECR and EC2 Deployment (`ecr-ec2-cicd.yml`)

This workflow builds, tests, and deploys the application to an EC2 instance using Docker images stored in Amazon ECR.

**Workflow Steps:**
1. **Test**: Builds the application and runs tests
2. **ECR**: Configures AWS credentials, builds a Docker image, and pushes it to Amazon ECR
3. **EC2**: Sets up the EC2 instance with required tools (Docker, AWS CLI), authenticates with ECR, pulls the latest image, and deploys it

---

### 3. Unit Tests and Vulnerability Scans (`unit-tests-and-vulnerability-scans.yml`)

This workflow focuses on code quality and security by running tests and performing vulnerability scans.

**Workflow Steps:**
1. **Unit Tests**: Runs all unit tests to ensure code functionality
2. **File System Scan**: Uses Trivy to scan source code for vulnerabilities
3. **Container Scan**: Builds the Docker image and scans it for vulnerabilities using Trivy

---

## Security Features

- JWT-based authentication
- Password encryption
- Role-based access control
- Vulnerability scanning for source code and containers
- Non-root user in Docker container
- Security headers configuration

---

## Contributing
Feel free to submit issues, fork the repository, and send pull requests. Contributions are welcome!

## Author
- **Pasindu Waidyarathna**
