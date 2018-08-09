package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.ScanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRequestRepository extends JpaRepository<ScanRequest, String> {
}
