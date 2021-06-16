package cl.bci.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.bci.user.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    @Override
    List<User> findAll();

    User name(@Param("name") String name);

}