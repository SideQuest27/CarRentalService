package com.karan.CarRentalService.Service;

import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest
{
    @Autowired
    CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;

    Customer outputCustomer;
    @BeforeEach
    void setUp()
    {
        outputCustomer= new Customer("Joe","Schmo","JoeSchmo@gmail.com",12345678);
    }

    @Test
    void createUserTest()
    {
        Customer inputCustomer= new Customer("Joe","Schmo","JoeSchmo@gmail.com",12345678);
        Mockito.when(customerRepository.save(inputCustomer)).thenReturn(outputCustomer);

        assertEquals(customerService.createUser(inputCustomer).getFirstName(),"Joe");
    }
}