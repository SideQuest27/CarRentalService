package com.karan.CarRentalService.Repository;

import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Enum.CarBrand;
import com.karan.CarRentalService.Error.CarNotFoundException;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepositoryTest
{
     //While testing make all the @Modifying in repository -> @Modifying(clearAutomatically=true)
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Car ferrari = new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
        testEntityManager.persist(ferrari);
    }

    @Test
    public void testFindById() {
        Car car = carRepository.findById(1L).orElse(null);
        assertNotNull(car);
        assertEquals(car.getCarId(), 1L);
    }
    @Test
    public void testFindByModel()
    {
        List<Car> car = carRepository.findByModel("812 Superfast");
        assertNotNull(car);
        assertEquals(car.get(0).getModel(),"812 Superfast");
    }
    @Test
    public void testFindByBrand()
    {
        List<Car> car = carRepository.findByBrand(CarBrand.FERRARI);
        assertEquals(car.get(0).getBrand(),CarBrand.FERRARI);
    }
    @Test
    public void findByBudget()
    {
       List<Car> car =carRepository.findCarsByBudget(600);
       assertEquals(car.get(0),carRepository.findById(1L).get());
    }
    @SneakyThrows
    @Transactional
    @Test
    public void carTakenTest()
    {
        carRepository.carTaken(1L);
        System.out.println(carRepository.findAll());
        assertEquals(carRepository.findById(1L).orElseThrow(()-> new CarNotFoundException()).getAvailable(),false);
    }
    @Transactional
    @Test
    public  void carReturnedTest()
    {
        carRepository.carTaken(1L);
        carRepository.carReturned(1L);
        assertEquals(carRepository.findById(1L).get().getAvailable(),true);
    }
}
