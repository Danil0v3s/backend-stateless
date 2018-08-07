package br.com.firstsoft.backendstateless.business.vo;

import lombok.Data;

@Data
public class ScanRequest {

    private String requestedUF;
    private String chNFe;
    private String nVersao;
    private String tpAmb;
    private String dhEmi;
    private String vNF;
    private String vICMS;
    private String digVal;
    private String cIdToken;
    private String cHashQRCode;

}
