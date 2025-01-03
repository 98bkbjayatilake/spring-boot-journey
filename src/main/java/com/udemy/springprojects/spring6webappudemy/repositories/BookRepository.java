package com.udemy.springprojects.spring6webappudemy.repositories;

import com.udemy.springprojects.spring6webappudemy.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}
