
package com.karan.CarRentalService.Controller;

import com.karan.CarRentalService.Entity.Invoice;
import com.karan.CarRentalService.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController
{
    @Autowired
    InvoiceService invoiceService;
    @GetMapping("/admin/invoice")
    public List<Invoice> showAllInvoices() {
        return invoiceService.showAllInvoices();
    }
    @GetMapping("/admin/invoice/outstanding")
    public List<Invoice> showAllOutStandingInvoices()
    {
        return invoiceService.showAllOutStandingInvoices();
    }
    @GetMapping("/admin/invoice/outdated")
    public List<Invoice> ShowAllOutDatedInvoices() {
        return invoiceService.showAllOutDatedInvoices();
    }
    @GetMapping("/user/invoice/{userId}")
    public Invoice UserInvoiceInfo(@PathVariable Long userId) {
        return invoiceService.userInvoiceInfo(userId);
    }
    @PutMapping("/user/pay")
    public String payInvoice(@RequestParam(required = true) Long id,@RequestParam(required = true) Integer amount)
    {
        return invoiceService.payInvoice(id,amount);
    }
}

