# Buy Recipes API

This project implements a REST API for a "Buy Recipes" feature, allowing users to buy recipes (collections of products) in addition to individual products.

## Technologies Used

- Kotlin 1.9.25
- Spring Boot 3.5.4
- Spring Data JPA
- H2 Database (Development)
- PostgreSQL (Production)
- Flyway for database migrations
- JUnit 5 for testing
- Springdoc OpenAPI for API documentation

## Architecture

The project follows a hexagonal architecture with clean architecture principles:

- **Domain Layer**: Contains business entities and interfaces, free from infrastructure concerns
- **Application Layer**: Contains use cases, DTOs, and service implementations
- **Infrastructure Layer**: Contains controllers, repository implementations, JPA entities, mappers, and configuration

The architecture strictly separates domain models from infrastructure concerns:
- Domain models are pure Kotlin classes without any JPA annotations
- JPA entities are in the infrastructure layer and are mapped to/from domain models
- Repositories work with domain models in their interfaces but use JPA entities in their implementations

## Features Implemented

- GET /products - List all available products
- GET /products/{id} - Get a product by ID
- GET /recipes - List all available recipes with their products
- GET /recipes/{id} - Get a recipe by ID with its products
- GET /v1/carts - List all carts
- GET /v1/carts/{id} - Get a cart by ID with its products and recipes
- GET /actuator/health - Check application health status
- GET /actuator/info - Get application information

## Running the Application

### Prerequisites

- JDK 17 or later
- Gradle

### Development Mode

```bash
./gradlew bootRun
```

The application will start on http://localhost:8080/api/v1

### API Documentation

Once the application is running, you can access the API documentation at:

http://localhost:8080/api/v1/swagger-ui.html

### Database

The application uses an H2 in-memory database in development mode. You can access the H2 console at:

http://localhost:8080/api/v1/h2-console

Connection details:
- JDBC URL: jdbc:h2:mem:recipesdb
- Username: sa
- Password: password

## Assumptions

### Recipe Pricing
We've removed the `totalPriceInCents` field from recipe responses because:

1. It was redundant - the client already receives all product prices and can calculate the total if needed
2. It forced clients to either trust our calculation (which could lead to inconsistencies) or recalculate locally (extra work)
3. By removing it, we simplify the API contract and reduce the risk of data inconsistencies
4. The client can calculate the total price if needed, using the same formula we would use on the server

This change follows the principle of keeping the API simple and avoiding redundant data.

## Testing

Run the tests with:

```bash
./gradlew test
```

## API Testing with HTTP Client

The project includes an `api-examples.http` file that contains examples of all API operations. You can use this file with:

- IntelliJ IDEA's HTTP Client
- Visual Studio Code with the REST Client extension
- Any other HTTP client tool that supports the `.http` format

To use the file:

1. Open `api-examples.http` in your IDE
2. Make sure the application is running
3. Click on the "Run" button next to any request to execute it
4. View the response directly in your IDE

This allows you to quickly test all API endpoints without needing to set up Postman or another external tool.

## Monitoring and Health Checks

The application includes Spring Boot Actuator for monitoring and health checks:

- **Health Check**: Access `/api/v1/actuator/health` to view the application's health status
  - Shows detailed information about various health indicators (database, disk space, etc.)
  - Returns HTTP 200 OK when the application is healthy
  - Returns HTTP 503 Service Unavailable when the application is unhealthy

- **Application Info**: Access `/api/v1/actuator/info` to view application information
  - Provides details about the application name, description, version, and development team
  - Useful for verifying deployment information

These endpoints are secured and only expose the necessary information for monitoring purposes.
