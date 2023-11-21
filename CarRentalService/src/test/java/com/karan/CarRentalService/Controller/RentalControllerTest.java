package com.karan.CarRentalService.Controller;

import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Entity.Rental;
import com.karan.CarRentalService.Enum.CarBrand;
import com.karan.CarRentalService.Service.RentalService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.time.Month;

@WebMvcTest(RentalController.class)
class RentalControllerTest
{

    @Autowired
    MockMvc mockMvc;
    @MockBean
    RentalService rentalService;

    Invoice outputInvoice;

    @BeforeEach
    void setUp()
    {
        Car car= new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
        Customer customer =  new Customer("Joe","Schmo","JoeSchmo@gmail.com",12345678);
        outputInvoice =new Invoice(LocalDate.now(),LocalDate.of(2023, Month.DECEMBER,12),3000,new Rental(LocalDate.of(2023, Month.DECEMBER,12),customer,car));
    }

    @SneakyThrows
    @Test
    void rentingTest()
    {
        Mockito.when(rentalService.renting(LocalDate.of(2023, Month.DECEMBER,12),1L,1L)).thenReturn(outputInvoice);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/rent")
                .param("endDate",LocalDate.of(2023, Month.DECEMBER,12).toString())
                .param("customerId",String.valueOf(1L))
                .param("carId",String.valueOf(1L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value(outputInvoice.getDueDate().toString()));
    }
}