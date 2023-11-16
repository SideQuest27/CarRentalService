package com.karan.CarRentalService.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="rentalId",referencedColumnName ="rentalId")
    private Rental rental;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Integer amountDue ;
    private Boolean paid=false;

    public Invoice() {
    }

    public Invoice(LocalDate issueDate, LocalDate dueDate, Integer amountDue, Rental rental) {
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.amountDue = amountDue;
        this.rental= rental;
    }
}

