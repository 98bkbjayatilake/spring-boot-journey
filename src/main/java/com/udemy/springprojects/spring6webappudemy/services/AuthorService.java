package com.udemy.springprojects.spring6webappudemy.services;

import com.udemy.springprojects.spring6webappudemy.domain.Author;
import com.udemy.springprojects.spring6webappudemy.domain.Book;

public interface AuthorService {
    Iterable<Author> findAll();
}
