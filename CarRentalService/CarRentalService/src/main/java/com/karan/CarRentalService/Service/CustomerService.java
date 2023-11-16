package com.karan.CarRentalService.Service;

import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Error.CustomerNotFoundException;
import com.karan.CarRentalService.Repository.CustomerRepository;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;
    public Customer createUser(Customer customer)
    {
        return customerRepository.save(customer);
    }
    @SneakyThrows
    public String deleteUser(Long id)
    {
        customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("USER WITH GIVEN ID DOES NOT EXIST!"));
        customerRepository.deleteById(id);
        return "CUSTOMER DELETED SUCCESSFULLY!";
    }
}
