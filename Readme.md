# Spring Blog API

RESTful CRUD API for a Blog using Spring Boot, MySQL, JPA and Hibernate.

[![Issues Closed](https://img.shields.io/github/issues-closed/CryptoSingh1337/spring-blog-api?color=red)](https://github.com/CryptoSingh1337/spring-blog-api/issues?q=is%3Aissue+is%3Aclosed)
[![Issues Open](https://img.shields.io/github/issues/CryptoSingh1337/spring-blog-api?color=green)](https://github.com/CryptoSingh1337/spring-blog-api/issues)
[![PRs Open](https://img.shields.io/github/issues-pr/CryptoSingh1337/spring-blog-api)](https://github.com/CryptoSingh1337/spring-blog-api/pulls)
![Last Commit](https://img.shields.io/github/last-commit/CryptoSingh1337/spring-blog-api?color=informational)
![PRs Welcome](https://img.shields.io/badge/prs-welcome-ff69b4)

## Run Locally

Clone the Repo

```bash
  git clone https://github.com/CryptoSingh1337/spring-blog-api.git
```

Change current working directory

```bash
  cd spring-blog-api
```

Run verify goal

```bash
  ./mvnw clean verify
```

Run Spring boot application (default port: 8080)

```bash
  java -jar target/*.jar
```

## Running Tests

To run tests, run the following command

```bash
  ./mvnw clean verify
```

## Documentation

[Documentation](https://cryptosingh1337.github.io/spring-blog-api)

## Tech Stack

**Server:** Spring Boot, Spring WebMvc, Spring Data JPA

**ORM:** Hibernate

**Database:** H2-Database, MySQL

**Testing:** JUnit 5, Mockito

**Documentation:** Spring Rest Docs

**Deployment:** Heroku