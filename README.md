# Employee Web Service

This project implements the CSW practical test requirements:

- Create database table for employees.
- Create `Employee` Java class with `id`, `name`, and `salary`.
- Implement `getEmployees`, `addEmployees(Employee e)`, and `updateEmployee(Employee e)`.
- Provide a web application to test the web services.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 database
- Thymeleaf web UI

## Run

```bash
mvn spring-boot:run
```

Open:

- Web application: http://localhost:8080/
- H2 console: http://localhost:8080/h2-console

H2 settings:

- JDBC URL: `jdbc:h2:file:./data/employees-db`
- User: `sa`
- Password: empty

## REST Web Service Methods

### getEmployees

```bash
curl http://localhost:8080/api/employees/getEmployees
```

### addEmployees

```bash
curl -X POST http://localhost:8080/api/employees/addEmployees \
  -H "Content-Type: application/json" \
  -d '{"name":"Nguyen Van Cuong","salary":2000.00}'
```

### updateEmployee

```bash
curl -X PUT http://localhost:8080/api/employees/updateEmployee \
  -H "Content-Type: application/json" \
  -d '{"id":1,"name":"Nguyen Van An Updated","salary":2200.00}'
```

## Database Table

The employee table is created in `src/main/resources/schema.sql`:

```sql
CREATE TABLE IF NOT EXISTS employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    salary DECIMAL(12, 2) NOT NULL
);
```
