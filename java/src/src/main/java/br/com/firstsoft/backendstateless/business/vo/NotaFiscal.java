package br.com.firstsoft.backendstateless.business.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_nota_fiscal")
public class NotaFiscal {

    @Id
    @JsonProperty("ChavedeAcesso")
    private String chaveAcesso;

    @JsonProperty("DadosBasicos")
    @OneToOne(mappedBy = "notaFiscal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DadosBasicos dadosBasicos;

    @JsonProperty("Emitente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnpj_emitente")
    private Emitente emitente;

    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.LAZY)
    private List<ItemNotaFiscal> items;

    public NotaFiscal() {
    }

    public void setParentToChildren() {
        this.dadosBasicos.setNotaFiscal(this);
        this.emitente.getNotaFiscalList().add(this);
    }

}
