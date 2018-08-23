package br.com.firstsoft.backendstateless.business.vo.embeddables;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ItemNotaFiscalPK implements Serializable {

    private String chaveAcessoNF;
    private String codigoEAN;

    public ItemNotaFiscalPK() {
    }

    public ItemNotaFiscalPK(String chaveAcessoNF, String codigoEAN) {
        this.chaveAcessoNF = chaveAcessoNF;
        this.codigoEAN = codigoEAN;
    }
}
