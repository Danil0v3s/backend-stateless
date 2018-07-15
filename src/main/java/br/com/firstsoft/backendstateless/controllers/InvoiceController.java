package br.com.firstsoft.backendstateless.controllers;


import br.com.firstsoft.backendstateless.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate")
    public void requestInvoiceScan(@RequestParam("url") String url) {
        invoiceService.requestInvoiceScan(url);
    }


}
