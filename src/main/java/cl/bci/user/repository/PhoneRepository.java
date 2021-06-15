package cl.bci.user.repository;

import org.springframework.data.repository.CrudRepository;

import cl.bci.user.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Integer>  {
    
}