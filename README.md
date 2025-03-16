# SISIMPUR Library

This is a Spring Boot project built with Gradle. The project uses a database running in Docker and exposes REST endpoints (with Swagger documentation enabled) to manage a library of authors, books, and users.

## Prerequisites

- **Java 11 or later:** Ensure you have the correct JDK installed.
- **Gradle:** The project uses the Gradle wrapper, so no separate Gradle installation is required.
- **Docker:** Make sure Docker is installed and running on your system. If you're on Windows or Mac, ensure Docker Desktop is up and running.

## Running the Database in Docker

The project includes a Docker Compose configuration that sets up the required database container.

1. Open a terminal in the project root directory.
2. Run the following command to start the database container:
   ```bash
   docker compose up
   ```
3. Verify that the container is running using your Docker Desktop UI or by checking with `docker ps`.

## Running the Spring Boot Server

Once your Docker container is up and running, start the Spring Boot application:

1. Open a new terminal in the project root directory.
2. Run the following command to start the application:
   ```bash
   ./gradlew bootrun
   ```
3. The server will start on port `8080` by default.

## Verifying the Setup

After starting the application, you can verify that everything is working by accessing the health endpoint:

- Open a browser or an API testing tool like Postman.
- Navigate to:
  ```
  http://localhost:8080/api/v1/health
  ```
- You should see a greeting message indicating that the server is running properly.

## API Documentation

### Swagger UI

The project integrates Swagger for interactive API documentation. To view the API docs:

- Open your browser and navigate to:
  ```
  http://localhost:8080/swagger-ui/index.html
  ```
- You will see a user-friendly interface that lets you explore and test the available endpoints.

---
