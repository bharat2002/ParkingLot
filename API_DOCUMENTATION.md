# API Documentation

Base URL: `http://localhost:8080/api`

---

## 1. Parking Lot

### Create Parking Lot
```
POST /parking-lots
Content-Type: application/json

Request:
{
  "name": "Tech Park",
  "address": "MG Road, Bangalore"
}

Response: 201 Created
{
  "id": 1,
  "name": "Tech Park",
  "address": "MG Road, Bangalore",
  "createdAt": "2025-08-04T08:00:00",
  "floorIds": []
}
```

### Get All Parking Lots
```
GET /parking-lots

Response: 200 OK
[
  { "id": 1, "name": "...", "address": "...", "createdAt": "...", "floorIds": [] }
]
```

---

## 2. Floor

### Add Floor
```
POST /floors
Content-Type: application/json

Request:
{
  "parkingLotId": 1,
  "floorNumber": "Ground"
}

Response: 201 Created
{
  "id": 1,
  "floorNumber": "Ground",
  "parkingLotId": 1,
  "spotIds": []
}
```

### List Floors by Parking Lot
```
GET /floors/by-lot/1

Response: 200 OK
[
  { "id":1, "floorNumber":"Ground", "parkingLotId":1, "spotIds":[] }
]
```

---

## 3. Spot

### Add Spot
```
POST /spots
Content-Type: application/json

Request:
{
  "floorId": 1,
  "spotNumber": "G1-C1",
  "spotType": "CAR"
}

Response: 201 Created
{
  "id": 1,
  "spotNumber": "G1-C1",
  "spotType": "CAR",
  "isAvailable": true,
  "floorId": 1
}
```

### List Available Spots
```
GET /spots/available/1

Response: 200 OK
[
  { "id":1, "spotNumber":"G1-C1", "spotType":"CAR", "isAvailable":true, "floorId":1 }
]
```

---

## 4. Vehicle

### Register Vehicle
```
POST /vehicles
Content-Type: application/json

Request:
{
  "licensePlate": "KA01AB1234",
  "vehicleType": "CAR",
  "ownerName": "Alice"
}

Response: 201 Created
{
  "id": 1,
  "licensePlate": "KA01AB1234",
  "vehicleType": "CAR",
  "ownerName": "Alice"
}
```

---

## 5. Ticket

### Issue Ticket (Entry)
```
POST /tickets
Content-Type: application/json

Request:
{
  "vehicleId": 1,
  "floorId": 1,
  "entryPanelId": 1
}

Response: 201 Created
{
  "id": 1,
  "entryTime": "2025-08-04T08:15:00",
  "exitTime": null,
  "active": true,
  "fee": 0.0,
  "spotId": 1,
  "vehicleId": 1
}
```

### Exit Ticket
```
POST /tickets/exit
Content-Type: application/json

Request:
{
  "ticketId": 1,
  "exitPanelId": 1
}

Response: 200 OK
{
  "id": 1,
  "entryTime": "2025-08-04T08:15:00",
  "exitTime": "2025-08-04T10:00:00",
  "active": false,
  "fee": 40.0,
  "spotId": 1,
  "vehicleId": 1
}
```

### List Active Tickets
```
GET /tickets/active

Response: 200 OK
[
  { "id":1, "entryTime":"...", "exitTime":null, "active":true, "fee":0.0, "spotId":1, "vehicleId":1 }
]
```

---

## 6. Panels

### Create Entry Panel
```
POST /entry-panels
Content-Type: application/json

Request:
{
  "parkingLotId": 1,
  "panelCode": "EP-1",
  "location": "Main Gate"
}

Response: 201 Created
{
  "id": 1,
  "panelCode": "EP-1", 
  "location": "Main Gate",
  "parkingLotId": 1
}
```

### Create Exit Panel
```
POST /exit-panels
Content-Type: application/json

Request:
{
  "parkingLotId": 1,
  "panelCode": "XP-1",
  "location": "Exit Gate"
}

Response: 201 Created
{
  "id": 1,
  "panelCode": "XP-1", 
  "location": "Exit Gate",
  "parkingLotId": 1
}
```

---

## Status Codes

- `200 OK` – Successful retrieval or update
- `201 Created` – Resource successfully created
- `204 No Content` – Deletion success
- `400 Bad Request` – Invalid input data
- `404 Not Found` – Resource not found
- `500 Internal Server Error` – Unhandled server exception

---

© 2025 Bharat Kumar Paliwal
