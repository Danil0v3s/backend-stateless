package br.com.firstsoft.backendstateless.repository;

import br.com.firstsoft.backendstateless.business.vo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByEmail(String email);

}
