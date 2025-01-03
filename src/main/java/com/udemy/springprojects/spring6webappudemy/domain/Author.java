package com.udemy.springprojects.spring6webappudemy.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //this id value will be utillized for persisting it in the database
    private String FirstName;
    private String LastName;

    //Set<Book> field to represent the books written by an author.
    //inverse side- simply maps the relationship using the mappedBy attribute
    @ManyToMany(mappedBy ="authors")//refers to the field named authors in the owning side(Book class)
    Set<Book> books=new HashSet<>();

    public Set<Book> getBooks(){
        return books;
    }

    public void setBooks(Set<Book> books_new){
        this.books=books_new;
    }

    public int hashCode(){
        return id!=null? id.hashCode():0;
    }

    public boolean equals(Object o){
        if(this==o) return true;
        if(id==null || getClass()!=o.getClass())return  false;
        Author a=(Author) o;
        return id!=null &&  id.equals(a.getId());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName){
        this.FirstName=FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }


}
