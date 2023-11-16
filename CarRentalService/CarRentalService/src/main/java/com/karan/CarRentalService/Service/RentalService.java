package com.karan.CarRentalService.Service;
import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Entity.Rental;
import com.karan.CarRentalService.Error.CarNotFoundException;
import com.karan.CarRentalService.Error.CarTakenException;
import com.karan.CarRentalService.Error.CustomerNotFoundException;
import com.karan.CarRentalService.Repository.CarRepository;
import com.karan.CarRentalService.Repository.CustomerRepository;
import com.karan.CarRentalService.Repository.InvoiceRepository;
import com.karan.CarRentalService.Repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class RentalService
{
    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Transactional
    @SneakyThrows
    public Invoice renting(LocalDate endDate, Long customerId, Long carId)
    {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("PLEASE SIGN UP BEFORE TRYING TO RENT!"));
        Car car= carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("A CAR WITH GIVEN BUDGET DOES NOT EXIST!"));
        if (carRepository.findById(carId).get().getAvailable())
        {
            carRepository.carTaken(carId);
        }
        else
        {
           throw new CarTakenException("THIS CAR IS CURRENTLY NOT AVAILABLE!");
        }
        Rental r = rentalRepository.save(new Rental(endDate,customer,car));
        return invoiceRepository.save(new Invoice(LocalDate.now(),endDate,r.getTotalCost(),r));
    }
}
