# Smart Parking Lot Management System

A Spring Boot backend with a built-in browser UI for managing parking lots, floors, spots, panels, vehicles, tickets, exits, and payments.

## Tech Stack

- Java with Spring Boot `3.5.4`
- Gradle wrapper
- Spring Web and Spring Data JPA
- H2 in-memory database
- Static frontend served from `src/main/resources/static`

## Run The App

```bash
./gradlew bootRun
```

Open:

```text
http://localhost:8081
```

The H2 console is available at:

```text
http://localhost:8081/h2-console
```

Use these H2 settings:

```text
JDBC URL: jdbc:h2:mem:parkingdb
User: sa
Password:
```

## Frontend Demo Flow

1. Open `http://localhost:8081`.
2. Click `Seed Demo` to create a parking lot, two floors, spots, entry and exit panels, and sample vehicles.
3. Use `Register Vehicle` to add a vehicle.
4. Use `Issue Ticket` to assign an available spot.
5. Open `Payments`, choose the active ticket, and click `Calculate Fee`.
6. Enter the ticket ID in `Take Payment` and click `Mark Paid`.

The UI talks directly to the backend APIs under `/api`, so it works from the same Spring Boot server without a separate frontend build step.

## API Summary

Base URL:

```text
http://localhost:8081/api
```

Main endpoints:

- `GET /parkinglots`
- `POST /parkinglots`
- `GET /floors/by-lot/{parkingLotId}`
- `POST /floors`
- `GET /spots/by-floor/{floorId}`
- `GET /spots/available/{floorId}`
- `POST /spots`
- `GET /vehicles`
- `POST /vehicles`
- `GET /entry-panels/by-lot/{parkingLotId}`
- `POST /entry-panels`
- `GET /exit-panels/by-lot/{parkingLotId}`
- `POST /exit-panels`
- `GET /tickets/active`
- `POST /tickets`
- `POST /tickets/exit`
- `POST /payments`
- `GET /payments/by-ticket/{ticketId}`

More endpoint examples are in [API_DOCUMENTATION.md](API_DOCUMENTATION.md).

## Project Structure

```text
src/main/java/com/bharatp/ParkingLotBackend
  controller/      REST API controllers
  dto/             Request and response objects
  entity/          JPA entities
  enums/           Vehicle, spot, and payment enums
  exception/       API error handling
  mapper/          Entity/DTO mapping helpers
  repository/      Spring Data repositories
  service/         Business logic and allocation/pricing strategies

src/main/resources
  application.properties
  static/          Browser UI
```

## Notes

- The app uses an in-memory H2 database, so data resets when the backend restarts.
- The configured server port is `8081`.
- The current Gradle toolchain is set to Java `24` in `build.gradle`.
