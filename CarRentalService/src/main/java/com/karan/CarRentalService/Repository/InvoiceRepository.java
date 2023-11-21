package com.karan.CarRentalService.Repository;
import com.karan.CarRentalService.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long>
{

    @Query("SELECT i FROM Invoice i WHERE i.paid =false")
    public List<Invoice> showAllOutStandingInvoices();

    @Query("SELECT i FROM Invoice i WHERE i.paid =true")
    public List<Invoice> showAllOutDatedInvoices();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Invoice i SET i.paid = true WHERE i.amountDue =?2 AND i.invoiceId =?1")
    public void payInvoice(Long id, Integer amount);

    @Query("SELECT i FROM Invoice i WHERE i.rental.customer.customerId =?1")
    public Invoice userInvoiceInfo(Long id);

    @Query("SELECT i FROM Invoice i WHERE i.amountDue=?1")
    public Optional<Invoice> checkingAmount(Integer amount);

}
