package com.karan.CarRentalService.Entity;
import com.karan.CarRentalService.Enum.CarBrand;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name ="uniqueCarPlate",columnNames ="registrationPlate"))
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @Enumerated(EnumType.STRING)
    private CarBrand brand;
    private String model;
    @Min(value = 1900,message ="Invalid Car Year Params!")
    @Max(value = 2024,message ="Invalid Car Year Params!")
    private Integer year;
    @Length(max = 8)
    private String registrationPlate;
    @NonNull
    private Boolean available;
    @NonNull
    private Integer chargePerDay;
    public Car() {
    }

    public Car(CarBrand brand, String model, Integer year, String registrationPlate, Boolean available, Integer chargePerDay) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationPlate = registrationPlate;
        this.available = available;
        this.chargePerDay = chargePerDay;
    }

}
