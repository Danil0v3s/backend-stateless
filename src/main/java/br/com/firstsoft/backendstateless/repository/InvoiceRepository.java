package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<NotaFiscal, String> {

}
