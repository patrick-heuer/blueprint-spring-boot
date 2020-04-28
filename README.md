![Logo](https://github.com/PKrause79/blueprint-spring-boot/blob/master/cat-blueprint-logo.png)

# Microservice Blueprint - Spring Boot
>
> (c) 2020 Patrick Krause
>
> Blueprint microsoervice with cats and their cat-owner as bounded context, using Spring Boot, H2 in-memory database<br>
> Mapper, Swagger / OpenAPI and OpenID Connect Security (via Keycloak)<br>

## LEARN
Clean Architecture

![Logo](https://github.com/PKrause79/blueprint-spring-boot/blob/master/CleanArchitecture.jpg)

## RUN


Create JAR & local run
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

