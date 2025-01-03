package com.udemy.springprojects.spring6webappudemy.repositories;

import com.udemy.springprojects.spring6webappudemy.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
