#!/bin/bash
echo "🚀 Starting Buy Recipes API Demo"
./gradlew bootRun &
sleep 10
echo "📡 API running at: http://localhost:8080/api/v1"
echo "📖 Swagger UI: http://localhost:8080/api/v1/swagger-ui.html"
echo "🏥 Health Check: http://localhost:8080/api/v1/actuator/health"