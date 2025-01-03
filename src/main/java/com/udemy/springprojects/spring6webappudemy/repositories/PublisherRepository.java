package com.udemy.springprojects.spring6webappudemy.repositories;

import com.udemy.springprojects.spring6webappudemy.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
}
