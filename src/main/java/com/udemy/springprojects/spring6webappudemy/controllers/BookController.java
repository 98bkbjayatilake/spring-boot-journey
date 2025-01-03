package com.udemy.springprojects.spring6webappudemy.controllers;

import com.udemy.springprojects.spring6webappudemy.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private  final BookService bookService;

    /*What's going to happen at runtime when Spring sees that we require a BookService it is going to have a Spring bean
    that implements the BookServiceImpl,that's going to be an implementation of BookService */
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    /*Model-Interface that defines a holder for model attributes.
    * Primarily designed for adding attributes to the model.*/

    /*addAttribute(String attributeName, Object attributeValue)-
    Add the supplied attribute under the supplied name.
     */

    /*What's happening here is when Spring calls, this method will pass in the model
    that will be an implementation of model*/

    @RequestMapping("/books")
    public String getBooks(Model model){
     model.addAttribute("books",bookService.findAll());
     //View for the Request Mapping//tell Spring boot to look for a view called books.
     return "books";
    }

}
