package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.ItemNotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, String> {

}
