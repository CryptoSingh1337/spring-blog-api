### Note: This API is deployed on Heroku, so initially it will take time to load.

# Spring Blog API

RESTful CRUD API for a Blog using Spring Boot, MySQL, JPA and Hibernate.

<p>
  <a href="https://github.com/CryptoSingh1337/spring-blog-api/issues?q=is%3Aissue+is%3Aclosed"><img src="https://img.shields.io/github/issues-closed/CryptoSingh1337/spring-blog-api?color=red" alt="Issues Closed"></a>
  <a href="https://github.com/CryptoSingh1337/spring-blog-api/issues"><img src="https://img.shields.io/github/issues/CryptoSingh1337/spring-blog-api?color=green" alt="Issues Open"></a>
  <a href="https://github.com/CryptoSingh1337/spring-blog-api/pulls"><img src="https://img.shields.io/github/issues-pr/CryptoSingh1337/spring-blog-api" alt="PRs Open"></a>
  <a href="https://github.com/CryptoSingh1337/spring-blog-api/commits"><img src="https://img.shields.io/github/last-commit/CryptoSingh1337/spring-blog-api?color=informational" alt="Last Commit"></a>
  <a href="https://github.com/CryptoSingh1337/spring-blog-api/pulls"><img src="https://img.shields.io/badge/prs-welcome-ff69b4" alt="PRs Welcome"></a>
</p>

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
