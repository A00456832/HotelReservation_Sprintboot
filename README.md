# HotelReservation_Springboot
By Imtiyaz Shaikh (A00456832)

## API:
The hotel reservation app provides one get, one post api to getch and insert the hotel details in the database.
During implementation, developer has used the development MYSQL server, the connection is established using ssh tunnel.

## Complexity:
1. The model calculates the 'finalCost' and then inserts into the database. Formula: finalCost = price * noDaysBooked
2. The 'id' column in auto-incrmented in the MySQL table.
3. Exception handling is done for error 404 and 500.

**_Screencapture are provided in the 'screenshots' directory._**

## GET:
**1. To fetch the details of all the hotels**
http://localhost:8090/hotel

OUTPUT:
```json
{
    "name": "My Hotel",
    "price": 555,
    "city": "Mumbai",
    "starRating": 9,
    "amenities": "Non-AC",
    "noDaysBooked": 2
}
```

**2. To fetch the details of specific hotel**
API CALL: http://localhost:8090/hotel/11

OUTPUT:
```json
{
    "message": "Successfully retrived : 11",
    "hotel": {
        "id": 11,
        "name": "Sanjeevi Inn",
        "price": 4321,
        "city": "Pune 11",
        "starRating": 5,
        "amenities": "Gaming Room",
        "noDaysBooked": 5,
        "finalCost": 21605
    }
}
```

**3. Exception Handling: Returns the 404 status code when requested hotel does not exist**
API CALL: http://localhost:8090/hotel/11433423

OUTPUT:
```json
{
    "message": "No value present",
    "hotel": null
}
```

## POST
1.http://localhost:8090/hotel

BODY: 
```json
{
    "name": "Sanjeevi Inn",
    "price": 4321,
    "city": "Pune 11",
    "starRating": 5,
    "amenities": "Gaming Room",
    "noDaysBooked": 5
}
```
