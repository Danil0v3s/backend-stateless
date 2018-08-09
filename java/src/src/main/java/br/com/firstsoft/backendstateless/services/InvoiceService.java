package br.com.firstsoft.backendstateless.services;

import br.com.firstsoft.backendstateless.business.dto.NotaFiscalDTO;
import br.com.firstsoft.backendstateless.business.enums.ScanRequestResult;
import br.com.firstsoft.backendstateless.business.vo.ScanRequest;
import br.com.firstsoft.backendstateless.business.vo.User;
import br.com.firstsoft.backendstateless.repository.ScanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Optional;

@Service
public class InvoiceService {

//    @Autowired
//    private InvoiceRepository invoiceRepository;

    @Autowired
    private ScanRequestRepository scanRequestRepository;

    @Autowired
    private Environment environment;

    public ResponseEntity<ScanRequest> requestInvoiceScan(ScanRequest scanRequest) {

        Optional<ScanRequest> optionalScanRequest = scanRequestRepository.findById(scanRequest.getCHashQRCode());
        ScanRequest managedScanRequest;

        if (optionalScanRequest.isPresent()) {
            managedScanRequest = optionalScanRequest.get();
            if (managedScanRequest.getScanRequestResult() == ScanRequestResult.ERROR) {
                generateInvoiceScan(managedScanRequest);
            }
        } else {
            managedScanRequest = saveScanRequest(scanRequest);
            generateInvoiceScan(managedScanRequest);
        }

        return ResponseEntity.ok(managedScanRequest);
    }

    private void generateInvoiceScan(ScanRequest managedScanRequest) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder uriBuilder = getUriBuilder();
        generateQueryParams(managedScanRequest, uriBuilder);

        Thread scanThread = new Thread(() -> {
            ResponseEntity responseEntity = restTemplate.getForEntity(uriBuilder.toUriString(), NotaFiscalDTO.class);
            NotaFiscalDTO notaFiscalDTO = (NotaFiscalDTO) responseEntity.getBody();

            if (notaFiscalDTO != null) {
                managedScanRequest.setScanRequestResult(ScanRequestResult.SUCCESS);
            } else {
                managedScanRequest.setScanRequestResult(ScanRequestResult.ERROR);
            }

            /*
             * TODO: GERAR A NOTA FISCAL QUE SERA SALVA NO BANCO, NORMALIZADA. DEFINIR ESTRUTURA DA ENTIDADE.
             */
            scanRequestRepository.save(managedScanRequest);
        });
        scanThread.start();
    }

    private ScanRequest saveScanRequest(ScanRequest scanRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        scanRequest.setUser(loggedUser);
        scanRequest.setRequestedAt(new Date());
        scanRequest.setScanRequestResult(ScanRequestResult.PROCESSING);
        return scanRequestRepository.save(scanRequest);
    }

    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder
                .fromHttpUrl(environment.getProperty("backend-stateless.scan-service.url", ""));
    }

    private void generateQueryParams(ScanRequest scanRequest, UriComponentsBuilder uriBuilder) {
        /*
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
    }

}