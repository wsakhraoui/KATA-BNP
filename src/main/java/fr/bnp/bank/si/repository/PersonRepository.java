package fr.bnp.bank.si.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.bnp.bank.si.entity.Person;

@Repository
public interface  PersonRepository extends CrudRepository<Person, Long> {

}
