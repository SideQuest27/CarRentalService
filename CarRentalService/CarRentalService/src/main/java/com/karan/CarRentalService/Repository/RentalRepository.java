package com.karan.CarRentalService.Repository;
import com.karan.CarRentalService.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Long>
{

}
