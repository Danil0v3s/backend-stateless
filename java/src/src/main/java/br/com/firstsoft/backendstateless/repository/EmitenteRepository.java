package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.Emitente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmitenteRepository extends JpaRepository<Emitente, String> {
}
