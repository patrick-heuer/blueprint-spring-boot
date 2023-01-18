![Logo](https://github.com/PKrause79/blueprint-spring-boot/blob/master/cat-blueprint-logo.png)

# Microservice Blueprint - Spring Boot
>
> (c) 2020 Patrick Heuer (formerly Krause)
>
> Blueprint microsoervice with cats and their cat-owner as bounded context, using Spring Boot, H2 in-memory database<br>
> Mapper, Swagger / OpenAPI and OpenID Connect Security (via Keycloak)<br>

## Clean Architecture
Clean Architecture push us to separate stable business rules (higher-level abstractions) from volatile technical details (lower-level details), defining clear boundaries. The main building block is the Dependency Rule : source code dependencies must point only inward, toward higher-level policies.

It should have the following characteristics:

* Testable
* Independent of frameworks
* Independent of the UI
* Independent of the database
* Independent of any external agency

![Logo](https://github.com/PKrause79/blueprint-spring-boot/blob/master/CleanArchitecture.jpg)

## RUN

Compile and run:
```
gradlew bootRun
```

## URL´s 

Cats: http://localhost:8080/api/v1/cats <br>
Health: http://localhost:8080/actuator <br>
Swagger: http://localhost:8080/swagger-ui.html <br>


## TODO´s
* Flyway migrations
* GraalVM
* Events (per AOP) "onBeforeExecute", "onAfterExecute"
* Exit on Except
* DTO´s containing entities instead of dublicating them
* request validation engine (https://lmonkiewicz.com/programming/get-noticed-2017/spring-boot-rest-request-validation/) -> Spring validators nutzen?
* IaC on AWS Lambda using Terraform (https://github.com/chaudharyarvind/Spring-boot-aws-lambda-terraform)

