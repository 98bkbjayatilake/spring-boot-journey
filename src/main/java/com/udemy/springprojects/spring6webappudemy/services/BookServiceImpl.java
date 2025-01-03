package com.udemy.springprojects.spring6webappudemy.services;

import com.udemy.springprojects.spring6webappudemy.domain.Book;
import com.udemy.springprojects.spring6webappudemy.repositories.BookRepository;
import org.springframework.stereotype.Service;

//@Service  indicates the context that we are creating a service here.
@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public  BookServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

}
