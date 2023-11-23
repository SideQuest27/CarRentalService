package com.karan.CarRentalService.Repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CustomerRepositoryTest
{
    //While testing make all the @Modifying in repository -> @Modifying(clearAutomatically=true)
}
