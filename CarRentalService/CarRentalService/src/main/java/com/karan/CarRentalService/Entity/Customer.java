package com.karan.CarRentalService.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name ="CustomerInfo",columnNames ={"email","phoneNumber"}))
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank(message ="First name Param. can't be left blank!")
    private String firstName;
    private String lastName;
    @Email(message = "PLEASE GIVE A VALID EMAIL!")
    private String email;
    @Min(10000000)
    @Max(99999999)
    Integer phoneNumber;
    public Customer() {
    }
    public Customer(String firstName, String lastName, String email, Integer phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
