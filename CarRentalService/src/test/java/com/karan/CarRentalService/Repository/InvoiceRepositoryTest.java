package com.karan.CarRentalService.Repository;
import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Entity.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceRepositoryTest
{

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp()
    {
        Invoice invoice = new Invoice(LocalDate.now(),LocalDate.of(2023, Month.DECEMBER,12),3000,new Rental());
        testEntityManager.persist(invoice);
    }

    @Test
    public void showAllOutstandingInvoiceTest()
    {
        List<Invoice> invoice = invoiceRepository.showAllOutStandingInvoices();
        assertEquals(invoice.get(0).getInvoiceId(),1L);
    }
    @Test
    public void showAllOutDatedInvoiceTest()
    {
        invoiceRepository.payInvoice(1L,3000);
        List<Invoice> invoice = invoiceRepository.showAllOutDatedInvoices();
        assertEquals(invoice.get(0).getInvoiceId(),1L);
    }
    @Test
    public void payInvoiceTest()
    {
        invoiceRepository.payInvoice(1L,3000);
        assertEquals(invoiceRepository.findById(1L).get().getPaid(),true);
    }
}
