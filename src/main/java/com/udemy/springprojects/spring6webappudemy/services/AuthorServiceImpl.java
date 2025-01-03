package com.udemy.springprojects.spring6webappudemy.services;

import com.udemy.springprojects.spring6webappudemy.domain.Author;
import com.udemy.springprojects.spring6webappudemy.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    private  final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
      this.authorRepository=authorRepository;
    }

    @Override
    public Iterable<Author> findAll() {
        return  authorRepository.findAll();
    }
}
