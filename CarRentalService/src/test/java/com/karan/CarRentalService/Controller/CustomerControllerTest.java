package com.karan.CarRentalService.Controller;
import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Service.CustomerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest
{
    @MockBean
    CustomerService customerService;
    @Autowired
    MockMvc mockMvc;
    Customer outputCustomer;

    @BeforeEach
    void setUp()
    {
        outputCustomer= new Customer("Joe","Schmo","JoeSchmo@gmail.com",12345678);
    }

    @SneakyThrows
    @Test
    void createUserTest()
    {
        Customer customer= new Customer("Joe","Schmo","JoeSchmo@gmail.com",12345678);
        Mockito.when(customerService.createUser(customer)).thenReturn(outputCustomer);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
                .content("{\n" +
                "  \"firstName\": \"Joe\",\n" +
                "  \"lastName\": \"Schmo\",\n" +
                "  \"email\": \"JoeSchmo@gmail.com\",\n" +
                "  \"phoneNumber\": 12345678\n" +
                "}\n").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(outputCustomer.getFirstName()));
    }
    @SneakyThrows
    @Test
    void deleteUserTest()
    {
        Mockito.when(customerService.deleteUser(1L)).thenReturn("CUSTOMER DELETED SUCCESSFULLY!");

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/user/delete/{id}",1L)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}