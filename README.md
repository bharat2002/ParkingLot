# 🅿️ Smart Parking Lot Management System

A **Scalable Smart Parking Lot Management System** backend built with **Java 17** and **Spring Boot 3.x**. This application manages parking lots with multiple floors and spot types, handles vehicle entry/exit via panels, issues tickets, calculates fees, and provides RESTful APIs for integration.

---

## 🚀 Features

- **Multi-Floor Support:** Create multiple floors per parking lot.
- **Spot Types:** Supports various spot types (BIKE, CAR, SUV, TRUCK).
- **Entry/Exit Panels:** Simulate physical panels issuing and resolving tickets.
- **Ticketing:** Generate tickets on entry, calculate fees on exit.
- **Fee Calculation Strategies:** Pluggable pricing strategies (e.g., hourly rates).
- **Real-Time Availability:** View available spots by floor and type.
- **Extensible Architecture:** Easily add reservation, surge pricing, or payment integrations.

---

## 📦 Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Persistence:** Spring Data JPA, H2 (development)
- **Build:** Maven
- **Mapping:** MapStruct (DTO mapping)
- **Logging:** SLF4J / Logback

---

## 📁 Project Structure

```
parking-lot-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/parkinglot/
│   │   │   ├── config/              # Swagger, DB config
│   │   │   ├── controller/          # REST controllers
│   │   │   ├── dto/                 # Request/Response DTOs
│   │   │   ├── entity/              # JPA entities
│   │   │   ├── enums/               # Enum definitions
│   │   │   ├── exception/           # Global handlers, custom exceptions
│   │   │   ├── mapper/              # Entity <-> DTO mappers
│   │   │   ├── repository/          # Spring Data repositories
│   │   │   ├── service/             # Service interfaces
│   │   │   └── service/impl/        # Service implementations
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
├── test/                            # Unit and integration tests
├── pom.xml                          # Maven config
└── README.md
```

---

## ⚙️ Installation & Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/<your-username>/parking-lot-backend.git
   cd parking-lot-backend
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access H2 Console (DEV):**  
   URL: `http://localhost:8080/h2-console`  
   JDBC URL: `jdbc:h2:mem:parkingdb`

---

## 🔗 API Overview

Please refer to the [API_DOCUMENTATION.md](API_DOCUMENTATION.md) for detailed endpoint specs, sample requests, and responses.

---

## 👨‍💻 Author

**Bharat Kumar Paliwal**  
[LinkedIn](https://www.linkedin.com/in/bharat-kumar-paliwal-b69533221)
---

