package br.com.firstsoft.backendstateless.services;

import br.com.firstsoft.backendstateless.business.vo.NotaFiscal;
import br.com.firstsoft.backendstateless.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void requestInvoiceScan(String url) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.getForEntity("http://localhost:5000?url=" + url, NotaFiscal.class);
        NotaFiscal notaFiscal = (NotaFiscal) responseEntity.getBody();

        if (notaFiscal != null) {
            notaFiscal.setParentToChildren();
            invoiceRepository.save(notaFiscal);
        }

    }

}