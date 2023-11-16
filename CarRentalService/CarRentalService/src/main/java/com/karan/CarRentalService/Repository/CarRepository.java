package com.karan.CarRentalService.Repository;
import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Enum.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>
{
    List<Car> findByModel(String model);
    List<Car> findByBrand(CarBrand brand);
    @Query(value = "SELECT c FROM Car c WHERE c.available = true")
    List<Car> findCarsByAvailability();
    @Query(value = "SELECT c FROM Car c WHERE c.chargePerDay <= ?1")
    List<Car> findCarsByBudget(Integer budget);

    @Modifying
    @Query("UPDATE Car c SET c.available = false WHERE c.carId = ?1")
    void carTaken(Long id);

    @Modifying
    @Query("UPDATE Car c SET c.available = true WHERE c.carId = ?1")
    void carReturned(Long id);
}
