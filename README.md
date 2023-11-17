# Car Rental Service

This web application is a car rental service built using Spring Boot and PostgreSQL.

## Table of Contents
- [Admin Operations](#admin-operations)
- [User Operations](#user-operations)

## Admin Operations

### Add Car
- **POST** `/admin`
  - *Expects car JSON body*
  - {
  "brand": "BENTLEY",
  "model": "ContinentalGt",
  "year": 2023,
  "registrationPlate": "BDY-69",
  "available": true,
  "chargePerDay": 2000
}


### Update Car
- **PUT** `/admin/update/{id}`
  - *Parameters:* 
    - `rp` (registration plate) = jbsd123
    - `cpd` (cost per day) = 400

### Delete Car
- **DELETE** `/admin/delete/{id}`

### Delete User
- **DELETE** `/admin/user/delete/{id}`

### Get All Invoices
- **GET** `/admin/invoice`

### Get All Outstanding Invoices
- **GET** `/admin/invoice/outstanding`

### Get All Outdated Invoices
- **GET** `/admin/invoice/outdated`

## User Operations

### Get All Cars
- **GET** `/user/car`

### Get All Available Cars
- **GET** `/user/car/available`

### Get Cars by Budget
- **GET** `/user/car/budget?max=1000`

### Get Cars by Model
- **GET** `/user/car/model?name=360 challenge stradale`

### Get Cars by Manufacturer
- **GET** `/user/car/brand?name=FERRARI`

### User Signup
- **POST** `/user/signup`
  - *Expects customer JSON body*
  - {
  "firstName": "Joe",
  "lastName": "Schmo",
  "email": "JoeSchmo@gmail.com",
  "phoneNumber": 12345678
}

### Get User Invoice by User ID
- **GET** `/user/invoice/{userId}`

### Pay Invoice
- **PUT** `/user/pay?id=123&amount=50`

### Rent Car
- **POST** `/user/rent?endDate=YYYY-MM-DD&customerId=1&carId=1`
