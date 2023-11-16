package com.karan.CarRentalService.Controller;
import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController 
{
    @Autowired
    CustomerService customerService;
    @PostMapping("/user/signup")
    public Customer createUser(@Valid @RequestBody Customer customer)
    {
        return customerService.createUser(customer);
    }
    @DeleteMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        return customerService.deleteUser(id);
    }
}
