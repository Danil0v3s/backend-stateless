package br.com.firstsoft.backendstateless.services;

import br.com.firstsoft.backendstateless.business.vo.NotaFiscal;
import br.com.firstsoft.backendstateless.business.vo.ScanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Service
public class InvoiceService {

//    @Autowired
//    private InvoiceRepository invoiceRepository;

    @Autowired
    private Environment environment;

    public void requestInvoiceScan(ScanRequest scanRequest) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(environment.getProperty("backend-stateless.scan-service.url", ""));

        /**
         * Reflexão
         * Seria melhor usar o método que cacheia
         * https://stackoverflow.com/questions/2638590/best-way-of-invoking-getter-by-reflection
         */
        for (Field declaredField : scanRequest.getClass().getDeclaredFields()) {
            try {
                String fieldName = declaredField.getName();
                Object value = new PropertyDescriptor(fieldName, ScanRequest.class).getReadMethod().invoke(scanRequest);
                uriBuilder.queryParam(fieldName, value);
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        ResponseEntity responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), NotaFiscal.class);
        NotaFiscal notaFiscal = (NotaFiscal) responseEntity.getBody();

        if (notaFiscal != null) {
            notaFiscal.setParentToChildren();
//            invoiceRepository.save(notaFiscal);
        }

    }

}