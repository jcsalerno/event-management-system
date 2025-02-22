# Event Management System

## Description

This is a Spring Boot application designed to manage events and subscriptions. It allows users to:

- Create and retrieve events.
- Subscribe to events, with an optional referral user.
- Handle exceptions and validations for a robust user experience.

The system is built using Java and Spring Boot, with H2 as the in-memory database for development purposes.

## Technologies Used

- Java 23
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Maven (dependency management)
- RESTful API design
- Exception Handling with custom exceptions
- DTOs (Data Transfer Objects) for structured responses

## Prerequisites

Before running the project, ensure you have the following installed:

- Java Development Kit (JDK) 17
- Maven (for dependency management)
- Postman or any API testing tool (for testing endpoints)

## How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/event-management-system.git
   cd event-management-system
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. Access the application:

    - The application will start on `http://localhost:8080`.
    - Use Postman or any API client to interact with the endpoints.

## API Endpoints

### Event Management

| Method | Endpoint          | Description                 | Request Body Example |
|--------|------------------|-----------------------------|----------------------|
| POST   | `/events`         | Create a new event         | `{ "title": "CodeCraft Summit", "location": "Online", "price": 0.0, "startDate": "2027-03-16", "endDate": "2027-03-18", "startTime": "19:00:00", "endTime": "21:00:00" }` |
| GET    | `/events`         | Get all events             | -                    |
| GET    | `/events/{prettyName}` | Get an event by its pretty name | - |

### Subscription Management

| Method | Endpoint                         | Description                          | Request Body Example |
|--------|---------------------------------|----------------------------------|----------------------|
| POST   | `/subscription/{prettyName}`   | Subscribe to an event (no referral) | `{ "name": "John Doe", "email": "john@example.com" }` |
| POST   | `/subscription/{prettyName}/{userId}` | Subscribe to an event with a referral | `{ "name": "John Doe", "email": "john@example.com" }` |

## Example Requests and Responses

### Create an Event

**Request:**

```http
POST /events
Content-Type: application/json
```

```json
{
  "title": "CodeCraft Summit",
  "location": "Online",
  "price": 0.0,
  "startDate": "2027-03-16",
  "endDate": "2027-03-18",
  "startTime": "19:00:00",
  "endTime": "21:00:00"
}
```

**Response:**

```json
{
  "eventId": 1,
  "title": "CodeCraft Summit",
  "prettyName": "codecraft-summit",
  "location": "Online",
  "price": 0.0,
  "startDate": "2027-03-16",
  "endDate": "2027-03-18",
  "startTime": "19:00:00",
  "endTime": "21:00:00"
}
```

### Subscribe to an Event

**Request:**

```http
POST /subscription/codecraft-summit
Content-Type: application/json
```

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

**Response:**

```json
{
  "subscriptionNumber": 1,
  "confirmationUrl": "http://codecraft.com/subscription/codecraft-summit/1"
}
```

## Project Structure

```
src/main/java/com/salerno/events/
├── controllers/            # REST controllers
├── dto/                    # Data Transfer Objects (DTOs)
├── exception/              # Custom exceptions
├── model/                  # Entity classes (Event, Subscription, User)
├── repository/             # Repository interfaces for database operations
├── services/               # Business logic and service layer
```

