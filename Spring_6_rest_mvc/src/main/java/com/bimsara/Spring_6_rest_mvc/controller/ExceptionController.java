package com.bimsara.Spring_6_rest_mvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
*@ControllerAdvice-This allows us to have a  global   exception handler and handle that globally and customize the response.
* The key technique here with using @ControllerAdvice is it gives you control over the response body and we can provide a response body if you need to
*/
//@ControllerAdvice
public class ExceptionController {
    //@ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException() {
        System.out.println("In exception handler");
        return ResponseEntity.notFound().build();
    }
}
