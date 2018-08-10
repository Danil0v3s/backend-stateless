package br.com.firstsoft.backendstateless.business.vo;

import br.com.firstsoft.backendstateless.business.vo.embeddables.DadosBasicosPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_dados_basicos_nf")
public class DadosBasicos {

    @EmbeddedId
    private DadosBasicosPK dadosBasicosPK;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

    public DadosBasicos() {
    }

    public DadosBasicos(DadosBasicosPK dadosBasicosPK) {
        this.dadosBasicosPK = dadosBasicosPK;
    }

}
