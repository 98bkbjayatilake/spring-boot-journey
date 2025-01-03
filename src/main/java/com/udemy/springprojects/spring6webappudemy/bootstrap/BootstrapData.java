package com.udemy.springprojects.spring6webappudemy.bootstrap;

import com.udemy.springprojects.spring6webappudemy.domain.Author;
import com.udemy.springprojects.spring6webappudemy.domain.Book;
import com.udemy.springprojects.spring6webappudemy.repositories.AuthorRepository;
import com.udemy.springprojects.spring6webappudemy.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*there is a Spring stereotype to indicate that I want the Spring contextto detect this and pick it up*/

/*CommandLineRunner-And basically it says when it detects this type of component on the class path  or in this context,
pick it up and it's going to execute the run method

* */
@Component
public class BootstrapData implements CommandLineRunner {
    //So this is going to get picked up and run every time that Spring Boot starts up
    private  final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //So on startup it'll automatically autowire in the implementations of the repositories that are being provided for us by Spring Data JPA
    public  BootstrapData(AuthorRepository authorRepository,BookRepository bookRepository){
        this.authorRepository=authorRepository;
        this.bookRepository=bookRepository;
    }
    @Override
    public void run(String... args) throws Exception {
      Author eric=new Author();
      eric.setFirstName("Eric");
      eric.setLastName("Evans");

      Book ddd=new Book();
      ddd.setTitle("Domain Driver Design");
      ddd.setIsbn("123456");

      Author ericSaved=authorRepository.save(eric);
      Book dddSaved=bookRepository.save(ddd);

      Author rod=new Author();
      eric.setFirstName("Rod");
      eric.setLastName("Johnson");

      Book noEJB=new Book();
      noEJB.setTitle("J2EE Development without EJB");
      noEJB.setIsbn("54757585");

      Author rodSaved=authorRepository.save(rod);
      Book noEJBSaved=bookRepository.save(noEJB);
//ericSaved is an object or instance of Author Class. So it has  a  property which is the books that is a collection of set type .
      ericSaved.getBooks().add(dddSaved);
      ////rodSaved is an object or instance of Author class . So it has  a  property which is the books that is a collection of set type .
      rodSaved.getBooks().add(noEJBSaved);

      //saved the authors with the books property(with the relationship of Book entity) in the author repository
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

      System.out.println("In Boostrap");
      System.out.println("AuthorCount:"+authorRepository.count());
      System.out.println("Book Count:"+bookRepository.count());

    }
}
