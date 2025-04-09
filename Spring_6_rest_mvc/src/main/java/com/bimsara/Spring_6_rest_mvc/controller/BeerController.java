package com.bimsara.Spring_6_rest_mvc.controller;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController()
//So this sets up a base mapping for the every method in this
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    /*if we just use @RequestMapping without specifying the HTTP method(like GET,POST, etc.),
    * then the method can be invoked for any HTTP action. But we usually don't want that-we want to restrict the method to respond only to a specific HTTP method,like GET.That's why we use @GetMapping instead*/
    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> ListBeers(){
        return beerService.listBeers();
    }

    /*
    @RequestMapping("/api/v1/beer/{beerId}")
    public Beer getBeerById(
        //@PathVariable binds the 'beerId' part of the URL to this method parameter
        //The string inside the @PathVariable("beerId") explicitly tells Spring which path variable to bind
        @PathVariable("beerId") UUID beerId){


        //just logging message
        log.debug("Get Beer by Id-in controller");

        return beerService.getBeerById(beerId);
    }
    */

    //explicitly restricted to GET only.
    @RequestMapping(value = "{beerId}",method=RequestMethod.GET)
    public Beer getBeerById(
            //@PathVariable binds the 'beerId' part of the URL to this method parameter
            //The string inside the @PathVariable("beerId") explicitly tells Spring which path variable to bind
            @PathVariable("beerId") UUID beerId){


        //just logging message
        log.debug("Get Beer by Id-in controller");

        return beerService.getBeerById(beerId);
    }
}
