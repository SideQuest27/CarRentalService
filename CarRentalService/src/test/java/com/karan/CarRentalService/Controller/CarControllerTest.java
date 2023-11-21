package com.karan.CarRentalService.Controller;

import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Enum.CarBrand;
import com.karan.CarRentalService.Error.CarNotFoundException;
import com.karan.CarRentalService.Service.CarService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(CarController.class)
class CarControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;
    Car outputCar;

    @BeforeEach
    private void setUpTest()
    {
        outputCar = new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
    }

    @SneakyThrows
    @Test
    void saveCarTest()
    {
        Car inputCar= new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
        Mockito.when(carService.saveCar(inputCar)).thenReturn(outputCar);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"brand\": \"FERRARI\",\n" +
                        "  \"model\": \"812 Superfast\",\n" +
                        "  \"year\": 2023,\n" +
                        "  \"registrationPlate\": \"asd8321\",\n" +
                        "  \"available\": true,\n" +
                        "  \"chargePerDay\": 600\n" +
                        "}\n")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @SneakyThrows
    @Test
    void updateCarTest()
    {
        Long id = 1L;
        String registrationPlate = "newPlate";
        Integer chargePerDay = 700;

        Mockito.when(carService.updateCar(id, registrationPlate, chargePerDay))
                .thenReturn("CAR DATA UPDATED!");

        mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/{id}", id)
                        .param("rp", registrationPlate)
                        .param("cpd", String.valueOf(chargePerDay))
                        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @SneakyThrows
    @Test
    void deleteCarTest()
    {
        Mockito.when(carService.deleteCar(1L)).thenReturn("CAR DELETED!");

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/delete/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void getCarsByBudgetTest() throws Exception
    {
        Mockito.when(carService.getCarsByBudget(50))
                .thenThrow(new CarNotFoundException("A CAR WITH GIVEN BUDGET DOES NOT EXIST!"));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/car/budget")
                        .param("max", String.valueOf(50)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @SneakyThrows
    @Test
    void getCarsByModelTest()
    {
        Mockito.when(carService.getCarsByModel("812 Superfast")).thenReturn(List.of(outputCar));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/car/model")
                .param("name","812 Superfast"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(outputCar.getModel()));
    }
}
