package com.karan.CarRentalService.Repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RentalServiceTest
{
    //While testing make all the @Modifying in repository -> @Modifying(clearAutomatically=true)
}