package com.udemy.springprojects.spring6webappudemy.bootstrap;

import com.udemy.springprojects.spring6webappudemy.domain.Author;
import com.udemy.springprojects.spring6webappudemy.domain.Book;
import com.udemy.springprojects.spring6webappudemy.domain.Publisher;
import com.udemy.springprojects.spring6webappudemy.repositories.AuthorRepository;
import com.udemy.springprojects.spring6webappudemy.repositories.BookRepository;
import com.udemy.springprojects.spring6webappudemy.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("In Bootstrap");
        System.out.println("AuthorCount: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

        Publisher p1 = new Publisher();
        p1.setPublisherName("My Publisher");
        p1.setAddress("123 Main");

        Publisher savedPublisher = publisherRepository.save(p1);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
