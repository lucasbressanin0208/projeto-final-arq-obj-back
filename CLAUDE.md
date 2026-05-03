# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
./mvnw spring-boot:run          # start the application
./mvnw test                     # run all tests
./mvnw -Dtest=ClassName test    # run a single test class
./mvnw clean package            # build JAR
```

On Windows use `mvnw.cmd` instead of `./mvnw`.

## Architecture

Layered REST API following the pattern: **Controller → Service → Repository → Model**

- `br.niaga.servija.controller` — Spring MVC REST controllers (empty stubs, endpoints not yet implemented)
- `br.niaga.servija.service` — Business logic layer (empty stubs)
- `br.niaga.servija.repository` — Spring Data JPA interfaces (empty stubs, must extend `JpaRepository<T, UUID>`)
- `br.niaga.servija.models` — JPA entity classes (`CategoriaServico`, `Servico`)
- `br.niaga.servija.dto` — Request/response DTOs (exclude the entity `id` field)

All entity primary keys use `UUID`. The `Servico` entity references a `Prestador` entity that does not yet exist in the codebase.

## Key Implementation Gaps

The project scaffold is in early stages. When implementing new functionality:

1. **Models** — add JPA annotations (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@ManyToOne`, etc.)
2. **Repositories** — extend `JpaRepository<ModelClass, UUID>`
3. **Services** — inject the repository and implement CRUD logic
4. **Controllers** — annotate with `@RestController`, `@RequestMapping`, and implement endpoints using the service layer

## Database

PostgreSQL hosted on Aiven Cloud. Connection config is in `src/main/resources/application.properties` (credentials included). SSL is required (`sslmode=require`). Schema is managed via JPA/Hibernate.

## API Documentation

SpringDoc OpenAPI is included. Once endpoints are implemented, Swagger UI is available at `/swagger-ui.html`.

## Tech Stack

- Java 25, Spring Boot 4.0.6
- Spring Data JPA + Hibernate
- PostgreSQL (runtime driver)
- SpringDoc OpenAPI 3.0.2
- JUnit 5 + MockMvc for testing