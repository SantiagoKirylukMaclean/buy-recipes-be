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
