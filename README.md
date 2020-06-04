# Backend ![Assemble Project](https://github.com/fhhagenberg-doodoo/backend/workflows/Assemble%20Project/badge.svg?branch=master&event=check_run) [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
***

# Spring Boot
## Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.0.RC1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.RC1/gradle-plugin/reference/html/#build-image)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.6.RELEASE/spring-framework-reference/languages.html#coroutines)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/html/spring-boot-features.html#boot-features-r2dbc)

### Guides
The following guides illustrate how to use some features concretely:

* [Acessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [R2DBC Homepage](https://r2dbc.io)

***

# Github Actions

With this config you can let your code be run on different VMs:

```YAML
jobs:
   build-project:
    strategy:
	  matrix:
	   os: [ubuntu-latest, macos-latest, windows-latest]
	 runs-on: ${{ matrix.os }}
```

***
# Problem Solving / Lesson learnt

## Github <-> Heroku Integration

- [https://devcenter.heroku.com/articles/github-integration#automatic-deploys](https://devcenter.heroku.com/articles/github-integration#automatic-deploys)

## Remove Credentials from source control


## Deploying Spring Boot to Heroku
- [https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku)

## Kotlin Linter
- [https://github.com/pinterest/ktlint](https://github.com/pinterest/ktlint)

## Why CORS is important
- [https://www.baeldung.com/spring-cors](https://www.baeldung.com/spring-cors)
- [https://enable-cors.org](https://enable-cors.org)

## Spring Boot / R2DBC / PostgreSQL

- [https://dzone.com/articles/bounty-spring-boot-and-postgresql-database](https://dzone.com/articles/bounty-spring-boot-and-postgresql-database)
- [http://zetcode.com/springboot/postgresql/](http://zetcode.com/springboot/postgresql/)
- [https://dzone.com/articles/spring-boot-and-postgresql](https://dzone.com/articles/spring-boot-and-postgresql)
- [https://springframework.guru/configuring-spring-boot-for-postgresql/](https://springframework.guru/configuring-spring-boot-for-postgresql/)

## Spring Boot / R2DBC / H2 (In-Memory DB)
- [https://www.baeldung.com/spring-boot-h2-database](https://www.baeldung.com/spring-boot-h2-database)
- [https://stackoverflow.com/questions/24223631/h2-postgresql-mode-seems-not-working-for-me](https://stackoverflow.com/questions/24223631/h2-postgresql-mode-seems-not-working-for-me)
- [https://stackoverflow.com/questions/7872693/running-postgresql-in-memory-only](https://stackoverflow.com/questions/7872693/running-postgresql-in-memory-only)

## Heroku PostgreSQL Connection
- [https://devcenter.heroku.com/articles/heroku-postgresql](https://devcenter.heroku.com/articles/heroku-postgresql)

## Github Action Docker Postgres
- [https://help.github.com/en/actions/configuring-and-managing-workflows/creating-postgresql-service-containers](https://help.github.com/en/actions/configuring-and-managing-workflows/creating-postgresql-service-containers)

## Code Coverage
- [https://stackoverflow.com/questions/56056206/configure-jacoco-with-gradle-and-kotlin-dsl](https://stackoverflow.com/questions/56056206/configure-jacoco-with-gradle-and-kotlin-dsl)
- [https://docs.gradle.org/current/userguide/jacoco_plugin.html](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
- [https://github.com/codecov/codecov-action](https://github.com/codecov/codecov-action)