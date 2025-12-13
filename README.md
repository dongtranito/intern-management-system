# Intern Management System

REST API quản lý thực tập sinh - Spring Boot + PostgreSQL

## Tech Stack

- Java 21, Spring Boot 3.4.12
- PostgreSQL, Spring Data JPA
- Bean Validation, Lombok

## Database

```sql
CREATE DATABASE intern_db;
```

**application.properties:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/intern_db
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```

## Run

```bash
mvn spring-boot:run
```

Base URL: `http://localhost:8080`


## API Documentation

📖 Xem chi tiết request/response examples tại: https://documenter.getpostman.com/view/33415374/2sB3dSRpKq

## Response Format

**Success:**
```json
{"status": "success", "data": {...}}
```

**Error:**
```json
{"status": "error", "message": "Không tìm thấy Intern ID: 99"}
```
