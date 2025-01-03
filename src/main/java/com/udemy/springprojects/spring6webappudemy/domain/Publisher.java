package com.udemy.springprojects.spring6webappudemy.domain;
import com.udemy.springprojects.spring6webappudemy.domain.Book;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String publisherName;
    private String address;
    private  String city;
    private  String state;
    private  String zip;

    /*
    * A publisher has many Books.
    * mappedby="publisher"->that's going to be One publisher to many books
    * */
    @OneToMany(mappedBy = "publisher")
   private Set<Book> books=new HashSet<>();

    @Override
    public int hashCode(){
        return id!=null? id.hashCode():0;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(id==null || getClass()!=o.getClass())return  false;
        Author a=(Author) o;
        return id!=null &&  id.equals(a.getId());
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //getters and setters for books property

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> newBooks){
        this.books=newBooks;
    }
}
