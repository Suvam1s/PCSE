#!/bin/bash
echo "Starting CRM PostgreSQL..." && docker compose up -d && echo "Starting Spring Boot..." && ./mvnw spring-boot:run
