package com.karan.CarRentalService.Service;
import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Enum.CarBrand;
import com.karan.CarRentalService.Repository.CarRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest
{
    @Autowired
    CarService carService;
    @MockBean
    CarRepository carRepository;

    Car outputcar;
    @BeforeEach
    void setUp()
    {
        outputcar = new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
        outputcar.setCarId(1L);
    }
    @Test
    void saveCarTest()
    {
        Car inputcar = new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
        inputcar.setCarId(1L);
        Mockito.when(carRepository.save(inputcar)).thenReturn(outputcar);

        assertEquals(outputcar.getModel(),carService.saveCar(inputcar).getModel());
    }
    @Test
    void getCarByIdTest()
    {
        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.ofNullable(outputcar));

        assertEquals(carService.getCarById(1L).getCarId(),1L);
    }
    @Test
    void getCarByAvailabilityTest()
    {
        Mockito.when(carRepository.findCarsByAvailability()).thenReturn(List.of(outputcar));

        assertEquals(carService.getCarsByAvailability(),List.of(outputcar));
    }
    @SneakyThrows
    @Test
    void getCarsByBudgetTest()
    {
        Mockito.when(carRepository.findCarsByBudget(600)).thenReturn(List.of(outputcar));

        assertEquals(carService.getCarsByBudget(600),List.of(outputcar));
    }
    @Test
    void  getCarsByModelTest()
    {
        Mockito.when(carRepository.findByModel("812 Superfast")).thenReturn(List.of(outputcar));

        assertEquals(carService.getCarsByModel("812 Superfast"),List.of(outputcar));
    }
    @Test
    void getCarsByManufacturerTest()
    {
        Mockito.when(carRepository.findByBrand(CarBrand.FERRARI)).thenReturn(List.of(outputcar));

        assertEquals(carService.getCarsByManufacturer(CarBrand.FERRARI.toString()),List.of(outputcar));
    }
}