# Flight Management System (Spring Boot + Hibernate)

## Overview

This project is a web application developed with Java and Spring Boot
that simulates the management of a small flight ecosystem. The
application allows the administration of agencies, users, flights,
tickets and offers through a structured backend built with Spring Boot
and Hibernate (JPA), combined with server-side rendering using
Thymeleaf.

The goal of this project is to demonstrate the implementation of a
layered backend architecture using widely adopted Java enterprise
technologies, focusing on clean structure, data persistence, entity
relationships and maintainable code organization.

The system follows a classic MVC approach where controllers handle HTTP
requests, services contain the business logic, repositories manage
database interaction through JPA, and Thymeleaf templates render the
views.

This repository is intended as part of a backend development portfolio
to showcase practical experience with Java enterprise frameworks.

------------------------------------------------------------------------

## Main Features

-   Management of travel agencies
-   Management of system users
-   Management of flights
-   Ticket registration and association with users and flights
-   Offer management linked to agencies and tickets
-   Full CRUD operations for the main entities
-   Validation of input data using Jakarta Validation
-   Persistence layer using Hibernate via Spring Data JPA
-   Server-side rendered views using Thymeleaf
-   Basic UI built with Bootstrap

------------------------------------------------------------------------

## Technology Stack

  Technology        Purpose
  ----------------- --------------------------------------
  Java 17           Core programming language
  Spring Boot       Backend application framework
  Spring MVC        Web layer and request handling
  Spring Data JPA   Data access abstraction
  Hibernate         ORM implementation
  MySQL             Relational database
  Thymeleaf         Server-side template engine
  Bootstrap         Interface styling
  Maven             Dependency management and build tool

------------------------------------------------------------------------

## Project Architecture

The project is structured using a layered architecture that separates
responsibilities across different components of the system.

Controller Layer\
Handles incoming HTTP requests and routes them to the appropriate
service.

Service Layer\
Contains the core business logic of the application.

Repository Layer\
Responsible for data access and communication with the database using
JPA.

Model Layer\
Defines the domain entities that map to database tables.

View Layer\
HTML templates rendered using Thymeleaf.

Typical flow inside the application:

Client Request\
→ Controller\
→ Service\
→ Repository\
→ Database

Response returned from controller\
→ Thymeleaf Template\
→ Rendered HTML page

------------------------------------------------------------------------

## Project Structure

src/main/java/com/miapp/vuelos

controller\
model\
repository\
service\
VuelosApplication.java

src/main/resources

templates\
static\
application.properties

The templates directory contains the Thymeleaf views for each entity
such as:

-   agencias.html
-   usuarios.html
-   vuelos.html
-   tickets.html
-   ofertas.html

------------------------------------------------------------------------

## Running the Project

1.  Clone the repository

git clone https://github.com/DNogueraa/vuelos.git

2.  Open the project in your preferred IDE (IntelliJ IDEA or Eclipse
    recommended).

3.  Configure the database connection in:

src/main/resources/application.properties

4.  Run the Spring Boot application

mvn spring-boot:run

5.  Open the application in your browser

http://localhost:8080

------------------------------------------------------------------------

## Learning Goals of the Project

This project was developed to strengthen practical knowledge in:

-   Building backend applications using Spring Boot
-   Implementing persistence with Hibernate and JPA
-   Designing entity relationships in relational databases
-   Structuring applications using layered architecture
-   Integrating server-side rendered views with Thymeleaf
-   Applying validation and clean code practices

------------------------------------------------------------------------

## Author

Daniel Noguera\
Java Backend Developer (in training)

Areas of interest:

-   Backend development with Java and Spring Boot
-   Database design and data persistence
-   Software architecture and maintainable systems
-   Enterprise application development
