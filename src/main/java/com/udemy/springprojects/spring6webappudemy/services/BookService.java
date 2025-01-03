package com.udemy.springprojects.spring6webappudemy.services;
import com.udemy.springprojects.spring6webappudemy.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
