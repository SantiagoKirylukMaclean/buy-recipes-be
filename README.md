# Buy Recipes API

This project implements a REST API for a "Buy Recipes" feature, allowing users to buy recipes (collections of products) in addition to individual products.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database](#database)
- [Testing](#testing)
- [API Testing with HTTP Client](#api-testing-with-http-client)
- [Monitoring and Health Checks](#monitoring-and-health-checks)
- [Assumptions](#assumptions)
- [Project Structure](#project-structure)
- [Implementation Journey](#implementation-journey)
- [Future Improvements](#future-improvements)

## Technologies Used

- Kotlin 1.9.25
- Spring Boot 3.5.4
- Spring Data JPA
- H2 Database (Development)
- PostgreSQL (Production)
- Flyway for database migrations
- JUnit 5 for testing
- Springdoc OpenAPI for API documentation
- Spring Boot Actuator for monitoring
- SLF4J with Logback for logging

## Architecture

The project follows a hexagonal architecture with clean architecture principles:

- **Domain Layer**: Contains business entities and interfaces, free from infrastructure concerns
- **Application Layer**: Contains use cases, DTOs, and service implementations
- **Infrastructure Layer**: Contains controllers, repository implementations, JPA entities, mappers, and configuration

The architecture strictly separates domain models from infrastructure concerns:
- Domain models are pure Kotlin classes without any JPA annotations
- JPA entities are in the infrastructure layer and are mapped to/from domain models
- Repositories work with domain models in their interfaces but use JPA entities in their implementations

### Project Structure

```
src/
├── main/
│   ├── kotlin/com/puetsnao/recipes/
│   │   ├── application/
│   │   │   ├── dto/           # Data Transfer Objects
│   │   │   └── service/       # Service implementations
│   │   ├── domain/
│   │   │   ├── exception/     # Domain exceptions
│   │   │   ├── model/         # Domain models
│   │   │   ├── repository/    # Repository interfaces
│   │   │   └── service/       # Service interfaces
│   │   └── infrastructure/
│   │       ├── config/        # Configuration classes
│   │       ├── controller/    # REST controllers
│   │       ├── entity/        # JPA entities
│   │       ├── exception/     # Exception handlers
│   │       ├── mapper/        # Entity-Domain mappers
│   │       └── repository/    # Repository implementations
│   └── resources/
│       ├── db/migration/      # Flyway migrations
│       └── application.yml    # Application configuration
└── test/
    └── kotlin/com/puetsnao/recipes/
        └── infrastructure/
            └── controller/    # Controller tests
```

## Features

- Product management: View products and their details
- Recipe management: View recipes with their products
- Cart management: Create carts, add/remove products and recipes
- Health monitoring: Check application health and info
- Statistics: View application statistics

## API Endpoints

### Products
- `GET /api/v1/products` - List all available products
- `GET /api/v1/products/{id}` - Get a product by ID

### Recipes
- `GET /api/v1/recipes` - List all available recipes with their products
- `GET /api/v1/recipes/{id}` - Get a recipe by ID with its products

### Carts
- `GET /api/v1/carts` - List all carts
- `GET /api/v1/carts/{id}` - Get a cart by ID with its products and recipes
- `POST /api/v1/carts/{id}/add_recipe` - Add a recipe to a cart
- `DELETE /api/v1/carts/{id}/recipes/{recipeId}` - Remove a recipe from a cart

### Monitoring
- `GET /api/v1/actuator/health` - Check application health status
- `GET /api/v1/actuator/info` - Get application information

### Statistics
- `GET /api/v1/stats` - Get application statistics (products, recipes, carts)

## Running the Application

### Prerequisites

- JDK 17 or later
- Gradle

### Development Mode

```bash
./gradlew bootRun
```

The application will start on http://localhost:8080/api/v1

### Quick Demo

You can use the provided demo script to quickly start the application and see the available endpoints:

```bash
./demo.sh
```

## API Documentation

Once the application is running, you can access the API documentation at:

http://localhost:8080/api/v1/swagger-ui.html

The OpenAPI specification is available at:

http://localhost:8080/api/v1/v3/api-docs

### Database

The application uses an H2 in-memory database in development mode. You can access the H2 console at:

http://localhost:8080/api/v1/h2-console

Connection details:
- JDBC URL: jdbc:h2:mem:recipesdb
- Username: sa
- Password: password

In production, the application is configured to use PostgreSQL.

### Database Schema

The database schema includes the following tables:
- `products`: Stores product information (id, name, price)
- `recipes`: Stores recipe information (id, name, description)
- `recipe_products`: Maps products to recipes with quantities
- `carts`: Stores cart information (id, total price)
- `cart_items`: Maps products to carts
- `cart_recipes`: Maps recipes to carts

## Assumptions

### Recipe Pricing
We've removed the `totalPriceInCents` field from recipe responses because:

1. It was redundant - the client already receives all product prices and can calculate the total if needed
2. It forced clients to either trust our calculation (which could lead to inconsistencies) or recalculate locally (extra work)
3. By removing it, we simplify the API contract and reduce the risk of data inconsistencies
4. The client can calculate the total price if needed, using the same formula we would use on the server

This change follows the principle of keeping the API simple and avoiding redundant data.

### Other Assumptions
- All products are always available (no inventory management)
- No authentication/authorization is required
- Recipes are pre-loaded in the database

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

## Implementation Journey

This project was developed incrementally, with each step building on the previous one. For a detailed look at the implementation journey, including commit history and key architectural decisions, see [implementation-journey.md](implementation-journey.md).

Key highlights:
- Started with core product functionality and gradually added recipes and cart features
- Refactored early to ensure clean architecture before adding complex features
- Enhanced user experience with improved error handling and validation
- Added operational features like monitoring and health checks

## Future Improvements

While the current implementation provides a solid foundation for the Buy Recipes feature, there are several areas that could be enhanced in future versions. For a comprehensive list of potential improvements, see [future-improvements.md](future-improvements.md).

Key areas for improvement:
- Performance and scalability (rate limiting, caching, async processing)
- Security enhancements (authentication, authorization, API keys)
- Monitoring and observability (distributed tracing, metrics collection)
- Architecture evolution (microservices patterns, CQRS, event sourcing)
- Additional features (user management, inventory, search functionality)
