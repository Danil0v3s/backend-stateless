package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
