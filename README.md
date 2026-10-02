# Library Management System 3

A Java-based Library Management System developed as a learning project to practice Java, Object-Oriented Programming, JDBC, SQL, and database-driven application design.

## About the Project

This project started as a basic Java/OOP Library Management System and was gradually redesigned to use a relational database.

The current version uses **Java + JDBC + MySQL** instead of storing the application's main data in Java collections.

The project is primarily focused on learning and understanding how different parts of a Java application work together.

## Current Features

* Admin login
* Member login
* Member management

  * Add member
  * Find member
  * Update member
  * Remove member
  * View all members
* Book management
* Borrowed-book management
* MySQL database integration
* JDBC-based database operations
* DAO-based database access
* Console-based user interface
* Environment variables for database configuration

## Technologies Used

* **Java**
* **JDBC**
* **MySQL**
* **Git**
* **GitHub**
* **IntelliJ IDEA**

## Project Structure

The project currently separates responsibilities into different classes, including:

* `LibraryManagementSystem3` — application entry point
* `ConsoleUI` — console interaction
* `InputHandler` — user input handling
* `LibraryService` — application/business logic
* `MemberDAO` — member database operations
* `BookDAO` — book database operations
* `AdminDAO` — admin database operations
* `BorrowedBooksDAO` — borrowed-book database operations
* Model classes such as `Member`, `Book`, `Admin`, `BorrowedBook`, and `User`

## Database

The application uses MySQL for persistent data storage.

Database credentials are provided through environment variables and are **not stored in this repository**.

## Learning Goals

The main purpose of this project is to learn how to:

* Apply Object-Oriented Programming concepts in a larger project
* Work with relational databases
* Use JDBC for database connectivity
* Write SQL queries from Java
* Understand DAO and service-layer responsibilities
* Manage database resources with try-with-resources
* Handle SQL exceptions
* Use Git and GitHub for version control
* Gradually refactor a project as its requirements become more complex

## Project Status

**In development / learning project**

The project is being developed incrementally, with new concepts and architectural improvements being introduced as part of the learning process.

## Future Plans

Possible future improvements include:

* Improving the application architecture
* Adding more robust validation and exception handling
* Introducing a GUI
* Further database improvements
* Eventually exploring Spring/Spring Boot integration

## Note

This project is primarily a learning project. The code and architecture may change significantly as new Java, database, and software-development concepts are learned.
