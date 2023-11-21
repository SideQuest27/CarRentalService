package com.karan.CarRentalService.Service;
import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Enum.CarBrand;
import com.karan.CarRentalService.Error.CarNotFoundException;
import com.karan.CarRentalService.Repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
public class CarService
{
    @Autowired
    CarRepository carRepository;

    public Car saveCar(Car car)
    {
        return carRepository.save(car);
    }
    public List<Car> getAllCars()
    {
        return carRepository.findAll();
    }
    @SneakyThrows
    public Car getCarById(Long id)
    {
        return carRepository.findById(id).orElseThrow(()->new CarNotFoundException("A CAR WITH GIVEN ID DOES NOT EXIST!"));
    }
    public List<Car> getCarsByAvailability()
    {
       return carRepository.findCarsByAvailability();
    }

    public List<Car>getCarsByBudget(Integer budget) throws CarNotFoundException
    {
        List<Car> cars=carRepository.findCarsByBudget(budget);
        if (cars.isEmpty())
        {
            throw new CarNotFoundException("A CAR WITH GIVEN BUDGET DOES NOT EXIST!");
        }
        return cars;
    }
    @SneakyThrows
    public List<Car> getCarsByModel(String model)
    {
        List<Car> cars=carRepository.findByModel(model);
        if (cars.isEmpty()) throw new CarNotFoundException("A CAR WITH GIVEN MODEL DOES NOT EXIST!");
        return cars;
    }
    public List<Car> getCarsByManufacturer(String brand)
    {
        try
        {
            CarBrand carBrand = CarBrand.valueOf(brand.toUpperCase());
            return carRepository.findByBrand(carBrand);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid enum value: " + brand);
            return Collections.emptyList();
        }
    }
    @SneakyThrows
    @Transactional
    public String updateCar(Long id, String registrationPlate, Integer chargePerDay)
    {
        Car car= carRepository.findById(id).orElseThrow(()->new CarNotFoundException("A CAR WITH GIVEN ID DOES NOT EXIST!"));
        System.out.println(car);
        String s="CAR DATA NOT UPDATED!";
        if (registrationPlate!=null && !(registrationPlate.equals(car.getRegistrationPlate())))
        {
            car.setRegistrationPlate(registrationPlate);
            s="CAR DATA UPDATED!";
        }
        else if (chargePerDay>0 && !(chargePerDay==car.getChargePerDay()))
        {
            car.setChargePerDay(chargePerDay);
            s="CAR DATA UPDATED!";
        }
        System.out.println(car);
        return s;
    }
    @SneakyThrows
    public String deleteCar(Long id)
    {
        carRepository.findById(id).orElseThrow(()->new CarNotFoundException("A CAR WITH GIVEN ID DOES NOT EXIST!"));
        carRepository.deleteById(id);
        return "CAR DELETED!";
    }
}
