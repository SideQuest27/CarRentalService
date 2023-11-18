package com.karan.CarRentalService.Service;

import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Error.TransactionFailedException;
import com.karan.CarRentalService.Repository.CarRepository;
import com.karan.CarRentalService.Repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService
{
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CarRepository carRepository;
    public List<Invoice> showAllInvoices() {
       return invoiceRepository.findAll();
    }

    public List<Invoice> showAllOutStandingInvoices()
    {
        return invoiceRepository.showAllOutStandingInvoices();
    }
    public List<Invoice> showAllOutDatedInvoices() {
        return invoiceRepository.showAllOutDatedInvoices();
    }

    public Invoice userInvoiceInfo(Long id) {
        return invoiceRepository.userInvoiceInfo(id);
    }
    @SneakyThrows
    @Transactional
    public String payInvoice(Long id, Integer amount)
    {
        String s="TRANSACTION FAILED PLEASE CHECK ID/AMOUNT";
        if (invoiceRepository.findById(id).orElseThrow(()-> new TransactionFailedException()).getAmountDue().equals(amount)&&invoiceRepository.findById(id).orElseThrow(()-> new TransactionFailedException()).getPaid().equals(false))
        {
            invoiceRepository.payInvoice(id,amount);
            carRepository.carReturned(invoiceRepository.findById(id).get().getRental().getCar().getCarId());
            s="TRANSACTION SUCCESSFUL";
        }
        return  s;
    }

}

