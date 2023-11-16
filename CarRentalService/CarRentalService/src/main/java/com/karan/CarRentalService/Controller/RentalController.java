package com.karan.CarRentalService.Controller;

import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class RentalController
{
    @Autowired
    RentalService rentalService;
    @PostMapping("/user/rent")
    public Invoice renting(@RequestParam LocalDate endDate, @RequestParam Long customerId, @RequestParam Long carId)
    {
        return rentalService.renting(endDate,customerId,carId);
    }
}
