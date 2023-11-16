# CarRentalService
This is car rental service web application built using Spring boot and postgreSQl.


POST     /admin  [TO ADD CAR’S] {expects car JSON body}
{
  "brand": "BENTLEY",
  "model": "ContinentalGt",
  "year": 2023,
  "registrationPlate": "BDY-69",
  "available": true,
  "chargePerDay": 2000
}
PUT        /admin/update/{id}?rp(registration plate))   =jbsd123&cpd(cost per day)=400 [TO UPDATE CARS]
DELETE /admin/delete/{id} [TO DELETE CARS]
GET        /user/car [TO GET ALL CARS]
GET        /user/car/available [TO GET ALL AVAILABLE CARS]
GET        /user/car/budget?max=1000 [TO GET CARS BY BUDGET]
GET        /user/car/model?name=360 challenge stradale [TO GET CARS BY MODEL]
GET        /user/car/brand?name=FERRARI [TO GET CARS BY MANUFACTURER]
POST      /user/signup [TO SIGNUP] {expects customer JSON body}
{
  "firstName": "Joe",
  "lastName": "Schmo",
  "email": "JoeSchmo@gmail.com",
  "phoneNumber": 12345678
}
DELETE  /admin/user/delete/{id}  [TO DELETE USER]
GET        /admin/invoice [TO GET ALL INVOICES]
GET        /admin/invoice/outstanding [TO GET ALL OUTSTANDING INVOICES]
GET        /admin/invoice/outdated [TO GET ALL OUTDATED  INVOICES]
GET        /user/invoice/{userId} [TO GET USER INVOICE BASED ON USER ID]
PUT        /user/pay?id=123&amount=50 [TO PAY THE INVOICE]
POST     /user/rent?endDate=YYYY-MM-DD&customerId=1&carId=1 [TO RENT CAR]
