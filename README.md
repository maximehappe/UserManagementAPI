# User Management System

## Introduction

This application is a User Management System built with Spring Boot and Gradle. It provides a RESTful API for creating, retrieving, and searching user accounts.

Key features:
- User creation with automatic ID generation
- User retrieval by ID
- User search by username (admin access required)
- Email validation

## Prerequisites

- JDK 17 or later
- IDE

## Setup and Running the Application

1. Clone the repository:
   ```
   git clone [repository URL]
   ```

2. Open the project folder in your IDE

3. Build the project:
   ```
   ./gradlew build
   ```

4. Run the application:
   ```
   ./gradlew bootRun
   ```

5. Once the application is running, open the following file in your web browser:
   ```
   src/main/resources/static/index.html
   ```
   This HTML file provides a simple interface to interact with the API.

## API Documentation

For detailed information about the API endpoints and how to use them, please refer to the `API_DOCUMENTATION.md` file in this repository.

## Testing

To run JUnit tests:
```
./gradlew test
```

You can use the provided `index.html` file to test the API endpoints through a simple web interface.
