
# Parking Lot Management System

A robust, modular, and extensible Spring Boot-based application to simulate real-world parking lot operations with support for multiple floors, dynamic spot allocation, and ticketing.

## Features

- Multiple floors and parking spot types (BIKE, CAR, TRUCK)
- Entry and exit panels
- Ticket issuance and payment processing
- Parking spot availability management
- RESTful APIs for system interaction

## Technologies Used

- Java 24
- Spring Boot 3.x
- Spring Web, Spring Data JPA
- H2 Database (in-memory)
- Maven

## Project Structure

```
parking-lot/
├── src/main/java/com/example/parkinglot/
│   ├── config/                  # Configuration classes
│   ├── controller/              # REST Controllers
│   ├── dto/                     # DTOs
│   ├── enums/                   # Enum types
│   ├── exception/               # Custom exceptions
│   ├── mapper/                  # DTO <-> Entity Mappers
│   ├── model/                   # Entity models
│   ├── repository/              # Spring Data Repositories
│   ├── service/                 # Interfaces for services
│   ├── service/impl/            # Implementations
│   ├── util/                    # Utilities (e.g., ID Generator)
│   └── ParkingLotApplication.java
├── resources/
│   └── application.properties
├── test/
│   └── (Unit tests)
└── pom.xml
```

## How to Run

```bash
git clone https://github.com/your-username/parking-lot.git
cd parking-lot
./mvnw spring-boot:run
```

## Sample Endpoints

- `POST /entry-panel/park` – Park a vehicle and get a ticket.
- `POST /exit-panel/unpark` – Unpark a vehicle and pay fees.
- `GET /parking-lot/availability` – View available spots.

## Future Enhancements

- Payment gateway integration
- Real-time occupancy dashboard
- Redis caching
- JWT-based authentication

---
