package com.karan.CarRentalService.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.Period;

@JsonIgnoreProperties(value ={"startDate","endDate","totalCost"})
@Getter
@Setter
@ToString
@Entity
public class Rental
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name ="customerId",referencedColumnName ="customerId")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name ="carId",referencedColumnName ="carId")
    private Car car;
    private LocalDate startDate= LocalDate.now();
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @Future(message ="End Date must be in the future!")
    private LocalDate endDate;
    private Integer totalCost;

    public Rental() {
    }
    public Rental(LocalDate endDate,Customer customer,Car car) {
        this.endDate = endDate;
        this.customer=customer;
        this.car=car;
        this.totalCost=calculateTotalCost(endDate,car);
    }
    public Integer calculateTotalCost(LocalDate endDate,Car car)
    {
        Integer days= Period.between(LocalDate.now(),endDate).getDays();
        Integer cpd=car.getChargePerDay();
        Integer total=days*cpd;
        return total;
    }
}
