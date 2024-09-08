# User Management API Documentation

## Overview

This document provides details on the RESTful API endpoints for the User Management System. The API allows for creating, retrieving, and searching user accounts. The API is built with Spring Boot and uses Gradle and Java.

## Base URL

All URLs referenced in the documentation have the following base:

```
http://localhost:8080/api/users
```

Note: The port number (8080) is assumed. If you're using a different port, please adjust accordingly.

## User Entity

The User entity represents the structure of user data in the system.

```java
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;

    public enum Role {
        USER,
        ADMIN
    }
}
```

### Properties

- `id`: Long - Auto-generated unique identifier
- `username`: String - User's username (required)
- `email`: String - User's email address (required, must be unique and in valid email format)
- `password`: String - User's password (required)
- `role`: Role - User's role, either USER or ADMIN

## Endpoints

### Create a New User

Creates a new user in the system.

- **URL:** `/`
- **Method:** `POST`
- **Auth required:** No
- **Permissions required:** None

#### Request Body

```json
{
  "username": "string",
  "email": "string",
  "password": "string",
  "role": "USER"
}
```

Note: The `role` field is optional. If not provided, it will likely default to "USER".

#### Success Response

- **Code:** 201 CREATED
- **Content example:**

```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "role": "USER"
}
```

### Get User by ID

Retrieves a user by their ID.

- **URL:** `/{id}`
- **Method:** `GET`
- **Auth required:** No
- **Permissions required:** None
- **URL Params:** 
  - `id=[long]`

#### Success Response

- **Code:** 200 OK
- **Content example:**

```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "role": "USER"
}
```

#### Error Response

- **Code:** 404 NOT FOUND
- **Content:** `{ error : "User not found" }`

### Search Users by Name

Searches for users by their username.

- **URL:** `/search`
- **Method:** `GET`
- **Auth required:** Yes
- **Permissions required:** SEARCH_USERS
- **URL Params:** 
  - `username=[string]`

#### Success Response

- **Code:** 200 OK
- **Content example:**

```json
[
  {
    "id": 1,
    "username": "johndoe",
    "email": "john@example.com",
    "role": "USER"
  },
  {
    "id": 2,
    "username": "janedoe",
    "email": "jane@example.com",
    "role": "ADMIN"
  }
]
```

## Error Handling

The API uses the following error codes:

- 400 Bad Request - This may occur if the email format is invalid
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 500 Internal Server Error

## Notes

- All endpoints return JSON
- The search endpoint requires admin role permissions, which are checked in the service layer (not      implemented)
- Passwords are not returned in any responses
- The email must be unique across all users
