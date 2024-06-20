package br.com.Rest_with._SpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Rest_with._SpringBoot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
