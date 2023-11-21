package com.karan.CarRentalService;
import com.karan.CarRentalService.Entity.Car;
import com.karan.CarRentalService.Entity.Customer;
import com.karan.CarRentalService.Enum.CarBrand;
import com.karan.CarRentalService.Repository.CarRepository;
import com.karan.CarRentalService.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class CarConfig
{
    @Bean
    CommandLineRunner commandLineRunner (CarRepository repository, CustomerRepository customerRepository)
    {
        return args->
        {
            Car ferrari = new Car(CarBrand.FERRARI, "812 Superfast", 2023, "asd8321", true, 600);
            Car lamborghini = new Car(CarBrand.LAMBORGHINI, "HuracanEvo", 2023, "XYZ456", true, 550);
            Car mclaren = new Car(CarBrand.MCLAREN, "765LT", 2022, "DEF789", true, 680);
            Car bentley = new Car(CarBrand.BENTLEY, "ContinentalGt", 2019, "BDY-69", true, 475);
            Car koenigsegg = new Car(CarBrand.KOENIGSEGG, "Jesko", 2023, "KGG123", true, 1600);
            Car bugatti = new Car(CarBrand.BUGATTI, "Chiron", 2022, "BUG123", true, 1500);
            Car porsche = new Car(CarBrand.PORSCHE, "911 GT2RS", 2020, "GRMNBST1", true, 650);

            Customer customer1= new Customer("Joe","Schmo","JoeSchmo@gmail.com",12345678);
            Customer customer2= new Customer("josie","miller","JosieMiller@gmail.com",87654321);
            customerRepository.saveAll(List.of(customer1,customer2));
            repository.saveAll(List.of(ferrari,lamborghini,mclaren,bentley,koenigsegg,bugatti,porsche));
        };
    }
}
