package com.karan.CarRentalService.Service;
import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Entity.Rental;
import com.karan.CarRentalService.Repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;
    @MockBean
    private InvoiceRepository invoiceRepository;
    Invoice outputInvoice;

    @BeforeEach
    void setUp()
    {
        outputInvoice = new Invoice(LocalDate.now(),LocalDate.of(2023, Month.DECEMBER,12),3000,new Rental());
    }
    @Test
    void testShowAllInvoices()
    {
        when(invoiceRepository.findAll()).thenReturn(List.of(outputInvoice));
        List<Invoice> result = invoiceService.showAllInvoices();
        assertEquals(1, result.size());
    }

    @Test
    void testShowAllOutStandingInvoices()
    {
        when(invoiceRepository.showAllOutStandingInvoices()).thenReturn(List.of(outputInvoice));
        List<Invoice> result = invoiceService.showAllOutStandingInvoices();
        assertEquals(1, result.size());
    }

    @Test
    void testShowAllOutDatedInvoices()
    {
        when(invoiceRepository.showAllOutDatedInvoices()).thenReturn(List.of());
        List<Invoice> result = invoiceService.showAllOutDatedInvoices();
        assertEquals(0, result.size());
    }
}