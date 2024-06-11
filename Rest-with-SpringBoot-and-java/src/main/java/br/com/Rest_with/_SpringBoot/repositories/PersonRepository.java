package br.com.Rest_with._SpringBoot.repositories;

import br.com.Rest_with._SpringBoot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
