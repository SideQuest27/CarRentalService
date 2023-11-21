package com.karan.CarRentalService.Repository;

import com.karan.CarRentalService.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{

}
