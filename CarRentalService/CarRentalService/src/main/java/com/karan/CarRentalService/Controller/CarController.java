package com.karan.CarRentalService.Controller;
import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CarController
{
    @Autowired
    CarService carService;
    @PostMapping("/admin")
    Car saveCar(@Valid @RequestBody Car car) {
        return carService.saveCar(car);
    }
    @PutMapping("/admin/update/{id}")
    String updateCar(@PathVariable Long id, @RequestParam(required = false) String rp, @RequestParam(required = false) Integer cpd) {
        return carService.updateCar(id, rp, cpd);
    }
    @DeleteMapping("/admin/delete/{id}")
    String deleteCars(@PathVariable Long id)
    {
        return carService.deleteCar(id);
    }
    @GetMapping("/user/car")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
    @GetMapping("/user/car/available")
    public List<Car> getCarsByAvailability() {
        return carService.getCarsByAvailability();
    }
    @GetMapping("/user/car/budget")
    public List<Car> getCarsByBudget(@RequestParam(required = true) Integer max) {
        return carService.getCarsByBudget(max);
    }
    @GetMapping("/user/car/model")
    public List<Car> getCarsByModel(@RequestParam String name) {
        return carService.getCarsByModel(name);
    }
    @GetMapping("/user/car/brand")
    public List<Car> getCarsByManufacturer(String name) {
        return carService.getCarsByManufacturer(name);
    }
}
