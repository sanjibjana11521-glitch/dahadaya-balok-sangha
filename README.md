# Balok Sangha Website

Spring Boot + Thymeleaf website for Balok Sangha, using PostgreSQL and native Hibernate Session/Transaction APIs.

## Local development

Requirements:
- Java 17+
- Maven 3.9+
- PostgreSQL 14+

Set these environment variables when needed:

```text
DB_URL=jdbc:postgresql://localhost:5432/balok_sangha
DB_USERNAME=postgres
DB_PASSWORD=your_password
```

The application listens on `PORT` when supplied, otherwise port `8080`.

## Render deployment

This project includes a root `Dockerfile` and is ready to deploy as a Render Web Service.

Use the same Render region for the Web Service and PostgreSQL database. Add these environment variables to the Web Service:

```text
DB_URL=jdbc:postgresql://<internal-host>/<database>
DB_USERNAME=<render-postgres-username>
DB_PASSWORD=<render-postgres-password>
```

Use the **Internal Database URL** from the Render PostgreSQL service and change its prefix to `jdbc:postgresql://`.

Do not commit database passwords to GitHub.

Hibernate uses `update`, so the `club_members` and `site_visit_counter` tables are created automatically when the application starts.

## Member seed data

After the application has started once and Hibernate has created the tables, run `database/member-insert-template.sql` against the Render PostgreSQL database if you want to load the supplied member list.
