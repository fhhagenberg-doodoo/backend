
# 💩 doodoo
## when there’s a shit-load of work to do
	

### Team Members

- Alexander Stelzhammer (S1810455024)
- Manuel Leibetseder (S1810455012)

### Introduction

doodoo is the web-based to-do app that lets you manage your tasks in an easy and comfortable way. doodoo follows the KISS principle: keep it simple, stupid: A minimalistic user interface displays your tasks (or doos) from top to bottom. All doos are grouped and sorted by due date. One of the most important aspects of keeping track of your work is to estimate time. Because of this, the time it takes for one doo to be completed can be estimated based on dumps. One dump is the average amount of time spent on the toilet during a load-off.


## Technical Background

### Frontend
The application’s frontend is implemented entirely using React[1] and TypeScript[2]. Basic unit tests for the frontend are written using the Jest[3] framework, E2E testing is done using Cypress[4]. To ensure consistent code quality across pull requests, the CI/CD pipeline will be configured to check linting rules using ESLint[5] and apply code formatting rules via Prettier[6]. The page will be automatically deployed to GitHub Pages[7].






### Backend
Using Spring Boot Framework[8] in combination with Kotlin [9]and the corresponding Coroutine framework to serve the data from the database to the frontend. Spring and Kotlin can be tested via Kotlin and Spring Boot dedicated testing frameworks.


### Database
Persisting the doo’s will be done with the help of PostgreSQL. A relational schema will be used to store the data of a user. Furthermore PostgreSQL configuration and scripts can be tested with Chef InSpec.


### Deployment Abstraction
To abstract the deployment of the individual services, Docker will be used with Dockerfiles for each of our implementations. Dockerfiles and a Docker-Compose configuration can be tested with Chef Inspec, to maintain deployability and for the ease of use.


### CI/CD
As no monorepo architecture is used, different CI/CD solutions will be tested for each repository.

________________
[1] https://reactjs.org/
[2] https://www.typescriptlang.org/
[3] https://jestjs.io/
[4] https://www.cypress.io/
[5] https://eslint.org/
[6] https://prettier.io/
[7] https://pages.github.com/
[8] https://spring.io/projects/spring-boot
[9] https://kotlinlang.org/


***
***

# Backend 

![Assemble Project](https://github.com/fhhagenberg-doodoo/backend/workflows/Assemble%20Project/badge.svg)  [![codecov](https://codecov.io/gh/fhhagenberg-doodoo/backend/branch/master/graph/badge.svg?token=STOG65KCML)](https://codecov.io/gh/fhhagenberg-doodoo/backend)  [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
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

## Unit Testing
- [https://www.baeldung.com/junit-5-kotlin](https://www.baeldung.com/junit-5-kotlin)
- [https://mike-plummer.github.io/2016-07-26-junit-5-with-spring-boot-plus-kotlin/?no-cache=1](https://mike-plummer.github.io/2016-07-26-junit-5-with-spring-boot-plus-kotlin/?no-cache=1)
- [https://phauer.com/2018/best-practices-unit-testing-kotlin/#tldr](https://phauer.com/2018/best-practices-unit-testing-kotlin/#tldr)
- [https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-blocking-test.html](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-blocking-test.html)
- [https://dev.to/kerooker/testing-a-spring-boot-application-with-kotlintest-pgd](https://dev.to/kerooker/testing-a-spring-boot-application-with-kotlintest-pgd)
- [https://github.com/pkainulainen/test-with-spring/tree/master/master-package/bonus-webinars-2019/unit-tests-rest-api](https://github.com/pkainulainen/test-with-spring/tree/master/master-package/bonus-webinars-2019/unit-tests-rest-api)
- [https://www.baeldung.com/mockmvc-kotlin-dsl](https://www.baeldung.com/mockmvc-kotlin-dsl)
- [https://medium.com/swlh/https-medium-com-jet-cabral-testing-spring-boot-restful-apis-b84ea031973d](https://medium.com/swlh/https-medium-com-jet-cabral-testing-spring-boot-restful-apis-b84ea031973d)

## Spring Boot Validation
Are not working due to no supported in Kotlin!

- [https://www.baeldung.com/spring-boot-bean-validation](https://www.baeldung.com/spring-boot-bean-validation)
- [https://www.baeldung.com/spring-data-rest-validators](https://www.baeldung.com/spring-data-rest-validators)
- [https://howtodoinjava.com/spring-restful/request-body-parameter-validation/](https://howtodoinjava.com/spring-restful/request-body-parameter-validation/)
- [https://dzone.com/articles/implementing-validation-for-restful-services-with](https://dzone.com/articles/implementing-validation-for-restful-services-with)
- [https://stackoverflow.com/questions/36515094/kotlin-and-valid-spring-annotation](https://stackoverflow.com/questions/36515094/kotlin-and-valid-spring-annotation)
- [https://www.toptal.com/java/spring-boot-rest-api-error-handling](https://www.toptal.com/java/spring-boot-rest-api-error-handling)

## Spring Boot Exception Handling
- [https://www.baeldung.com/exception-handling-for-rest-with-spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)

## Slack Webhook
- [https://github.com/marketplace/actions/slack-notify](https://github.com/marketplace/actions/slack-notify)

## Dockerfile
- [https://thepracticaldeveloper.com/2017/12/11/dockerize-spring-boot/](https://thepracticaldeveloper.com/2017/12/11/dockerize-spring-boot/)
- [https://www.callicoder.com/spring-boot-docker-example/](https://www.callicoder.com/spring-boot-docker-example/)

## Heroku Slack Integration
- [https://devcenter.heroku.com/articles/chatops#connecting-ci-and-other-pipeline-events-to-your-slack](https://devcenter.heroku.com/articles/chatops#connecting-ci-and-other-pipeline-events-to-your-slack)


## Docker Build Push Image Github Actions
- [https://github.com/docker/build-push-action](https://github.com/docker/build-push-action)
