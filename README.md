# HotelReservation_Springboot
By Imtiyaz Shaikh (A00456832)

## API:
The hotel reservation app provides handful of GET, POST, DELETE apis to maintain the hotel reservation system using MySQL database. 

## Complexity:
1. 3 Modeles with 1:Many relationship
        a. Hotel
        b. Reservation : 1 Hotel can have many Reservations
        c. Guest       : 1 Reservation can have many guest
2. The model calculates the 'finalPrice' and then inserts into the database. Formula: finalPrice = (price per day) * ( (Checkout day) - (Checkin day) )
3 Cascaded delete funtionality is used
4. Custom error scenarios implemented
        a. Checkout date must be less than checkin date
        b. Either of checkin date or checkout date must not be null
        c. Minimum 1 guest must be included while making a reservation
	d. Overlapping of reservation is not allowed


**_Screencapture are provided in the 'screenshots' directory._**

## HOTEL:
**1. POST : To add Hotel entry**

API CALL: http://localhost:8090/hotel

INPUT:
```json
{
	"name": "Best Hotel",
	"price": 456,
	"city": "Mumbai",
	"starRating": 4
}
```
OUTPUT:
```json
{
    "message": "Successfully Created by id : 12",
    "hotel": {
        "id": 12,
        "name": "Best Hotel",
        "price": 456,
        "city": "Mumbai",
        "reservationList": [],
        "starRating": 4,
        "available": true
    }
}
```
**2. GET: To get list of hotels**

API CALL: http://localhost:8090/hotel

OUTPUT:
```json
{
    "message": "Successfully retrived",
    "hotels": [
        {
            "id": 1,
            "name": "Good Hotel",
            "price": 400,
            "city": "Mumbai",
            "reservationList": [],
            "starRating": 3,
            "available": true
        },
        {
            "id": 2,
            "name": "Better Hotel",
            "price": 500,
            "city": "Pune",
            "reservationList": [],
            "starRating": 4,
            "available": true
        },
        {
            "id": 12,
            "name": "Best Hotel",
            "price": 778,
            "city": "Chennai",
            "reservationList": [],
            "starRating": 5,
            "available": true
        }
    ]
}
```
**3. GET: To fetch the details of specific hotel**

API CALL: http://localhost:8090/hotel/1

OUTPUT:
```json
{
    "message": "Successfully retrived : 1",
    "hotel": {
        "id": 1,
        "name": "Good Hotel",
        "price": 400,
        "city": "Mumbai",
        "reservationList": [],
        "starRating": 3,
        "available": true
    }
}
```
**4. GET: specific hotel details**

API CALL: http://localhost:8090/hotel/1

OUTPUT:
```json
{
    "message": "Successfully retrived : 1",
    "hotel": {
        "id": 1,
        "name": "Good Hotel",
        "price": 400,
        "city": "Mumbai",
        "reservationList": [],
        "starRating": 3,
        "available": true
    }
}
```

**5. POST: To make a reservation against specific hotel**

API CALL: http://localhost:8090/reservation/12

```json
{
    "checkinDate": "14-02-2020",
    "checkoutDate": "16-02-2020",
    "guestList":[{
        "firstName" : "Narendra",
        "lastName" : "Modi",
        "gender" : "Male"
    },{
        "firstName" : "Rahul",
        "lastName" : "Gandhi",
        "gender" : "Male"
    }]
}
```
OUTPUT:
```json
{
    "message": "Successfully Created by id : 13",
    "reservation": {
        "id": 13,
        "guestList": [
            {
                "id": 14,
                "firstName": "Narendra",
                "lastName": "Modi",
                "gender": "Male"
            },
            {
                "id": 15,
                "firstName": "Rahul",
                "lastName": "Gandhi",
                "gender": "Male"
            }
        ],
        "checkinDate": "14-02-2020",
        "checkoutDate": "16-02-2020",
        "totalPrice": 1556.0,
        "reservationDateTime": "2022-03-11T20:36:23.748857"
    }
}
```
**6. GET: Reservation details for specific reservation id**

API CALL: http://localhost:8090/reservation/13

OUTPUT:
```json
{
    "message": "Successfully retrived : 13",
    "reservation": {
        "id": 13,
        "guestList": [
            {
                "id": 14,
                "firstName": "Narendra",
                "lastName": "Modi",
                "gender": "Male"
            },
            {
                "id": 15,
                "firstName": "Rahul",
                "lastName": "Gandhi",
                "gender": "Male"
            }
        ],
        "checkinDate": "14-02-2020",
        "checkoutDate": "16-02-2020",
        "totalPrice": 1556.0,
        "reservationDateTime": "2022-03-11T20:36:23.748857"
    }
}
```
**7. DELETE: Remove the reservation**

API CALL: http://localhost:8090/reservation/36

OUTPUT:
```json
{
    "message": "Successfully deleted : 13",
    "reservation": null
}
```
If we provide non-existent reservation id then below response will be shown:
```json
{
    "message": "Please provide valid reservation Id.",
    "reservation": null
}
```

**8. POST - Custom error Handling: While making a reservation, checkout date can not be prior to checkin date.**

API CALL: http://localhost:8090/reservation/24

INPUT:
```json
{
    "checkinDate": "14-02-2020",
    "checkoutDate": "11-02-2020",
    "guestList":[{
        "firstName" : "Narendra",
        "lastName" : "Modi",
        "gender" : "Male"
    },{
        "firstName" : "Rahul",
        "lastName" : "Gandhi",
        "gender" : "Male"
    }]
}
```

OUTPUT:
```json
{
    "message": "Error: Checkout date can not be prior to checkin date.",
    "reservation": null
}
```

**## POST - Custom error Handling: While making a reservation, checkin date is not provided.**

API CALL: http://localhost:8090/hotel

INPUT:
```json
{
    "checkoutDate": "11-02-2020",
    "guestList":[{
        "firstName" : "Narendra",
        "lastName" : "Modi",
        "gender" : "Male"
    },{
        "firstName" : "Rahul",
        "lastName" : "Gandhi",
        "gender" : "Male"
    }]
}
```
OUTPUT: 
```json
{
    "message": "Error: Either checkin date or checkout date is null.",
    "reservation": null
}
```

**## POST - Custom error Handling: While making a reservation, checkin date is not provided.**
INPUT:
```json
{
    "checkinDate": "22-02-2020",
    "checkoutDate": "24-02-2020"
}
```
OUTPUT:
```json
{
    "message": "Error: At least 1 guest must be provided.",
    "reservation": null
}
```
