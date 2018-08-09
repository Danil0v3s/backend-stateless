package br.com.firstsoft.backendstateless.business.vo;

import br.com.firstsoft.backendstateless.business.enums.ScanRequestResult;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_scan_request")
public class ScanRequest implements Serializable {

    @Id
    private String cHashQRCode;
    private String requestedUF;
    private String chNFe;
    private String nVersao;
    private String tpAmb;
    private String dhEmi;
    private String vNF;
    private String vICMS;
    private String digVal;
    private String cIdToken;
    private Date requestedAt;
    @OneToOne
    private User user;
    private ScanRequestResult scanRequestResult;
}
