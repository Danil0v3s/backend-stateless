package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.DadosBasicos;
import br.com.firstsoft.backendstateless.business.vo.embeddables.DadosBasicosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosBasicosRepository extends JpaRepository<DadosBasicos, DadosBasicosPK> {
}
