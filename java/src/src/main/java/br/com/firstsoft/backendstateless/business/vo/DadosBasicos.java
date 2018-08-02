package br.com.firstsoft.backendstateless.business.vo;

import br.com.firstsoft.backendstateless.business.vo.embeddables.DadosBasicosPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_dados_basicos_nf")
public class DadosBasicos {

    @EmbeddedId
    private DadosBasicosPK dadosBasicosPK;

    @JsonProperty("DataSaidaEntrada")
    private String dataSaidaEntrada;

    @JsonProperty("DatadeEmissao")
    private String dataEmissao;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("Numero")
    private String numero;

    @JsonProperty("Serie")
    private String serie;

    @JsonProperty("ValorTotaldaNotaFiscal")
    private String valorNota;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

    public void setPrimaryKey() {
        this.dadosBasicosPK = new DadosBasicosPK(dataEmissao, modelo, numero, serie, valorNota);
    }
}
