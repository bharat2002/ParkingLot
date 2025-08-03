
# Parking Lot System API Documentation

## Base URL
```
http://localhost:8081
```

## Endpoints

### 1. Park a Vehicle

`POST /entry-panel/park`

**Request:**
```json
{
  "vehicleNumber": "RJ14AB1234",
  "vehicleType": "CAR"
}
```

**Response:**
```json
{
  "ticketId": "TCKT-0001",
  "spotId": "SPOT-1",
  "floor": 0,
  "vehicleNumber": "RJ14AB1234",
  "entryTime": "2025-08-03T15:32:00"
}
```

### 2. Unpark Vehicle

`POST /exit-panel/unpark`

**Request:**
```json
{
  "ticketId": "TCKT-0001"
}
```

**Response:**
```json
{
  "ticketId": "TCKT-0001",
  "exitTime": "2025-08-03T17:00:00",
  "totalAmount": 30.00,
  "status": "PAID"
}
```

### 3. Get Availability

`GET /parking-lot/availability`

**Response:**
```json
{
  "floors": [
    {
      "floorNumber": 0,
      "spotsAvailable": {
        "BIKE": 5,
        "CAR": 10,
        "TRUCK": 2
      }
    }
  ]
}
```

---

## Status Codes

- `200 OK` – Successful request
- `400 Bad Request` – Invalid input
- `404 Not Found` – Resource not found (e.g., invalid ticket)
- `500 Internal Server Error` – Server side issue

## Error Example

```json
{
  "timestamp": "2025-08-03T17:05:00",
  "status": 404,
  "error": "Ticket Not Found",
  "message": "No ticket found with ID TCKT-0009"
}
```

---
