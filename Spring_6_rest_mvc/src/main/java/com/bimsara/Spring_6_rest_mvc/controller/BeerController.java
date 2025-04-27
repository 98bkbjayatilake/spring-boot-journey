package com.bimsara.Spring_6_rest_mvc.controller;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController()
//So this sets up a base mapping for the every method in this
//@RequestMapping("/api/v1/beer")
public class BeerController {

    public static final String BEER_PATH="/api/v1/beer";
    public static final String BEER_PATH_ID=BEER_PATH+ "/{beerId}";

    private final BeerService beerService;

    /*if we just use @RequestMapping without specifying the HTTP method(like GET,POST, etc.),
    * then the method can be invoked for any HTTP action. But we usually don't want that-we want to restrict the method to respond only to a specific HTTP method,like GET.That's why we use @GetMapping instead*/
    @GetMapping(value =BEER_PATH )
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
    @GetMapping(value =BEER_PATH_ID)
    public Beer getBeerById(
            //@PathVariable binds the 'beerId' part of the URL to this method parameter
            //The string inside the @PathVariable("beerId") explicitly tells Spring which path variable to bind
            @PathVariable("beerId") UUID beerId){


        //just logging message
        log.debug("Get Beer by Id-in controller");
        //If the optional does not have a value and the controller has a logic to throw the not found exception to trigger the 404 error
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    //POST is a create request
    @PostMapping(BEER_PATH)
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer){
        Beer saveBeer=beerService.saveNewBeer(beer);

        HttpHeaders headers=new HttpHeaders();
        headers.add("Location","/api/v1/beer/" +saveBeer.getId().toString());
        return  new ResponseEntity(headers,HttpStatus.CREATED);
    }

    //PUT is used to update a resource
    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(
            @PathVariable("beerId") UUID beerId,
            @RequestBody Beer beer)
    {
      beerService.updateBeerById(beerId,beer);
      //We receive a request and everything happened normally.
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable ("beerId") UUID beerId){
        beerService.deleteById(beerId);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /*
    * @ExceptionHandler(NotFoundException.class) tells Spring Boot: "If any NotFoundException is thrown inside this controller
     , run this method instead of crashing"
    *only work for methods of BeerController */
    /*
     * You manually build a 404 response using ResponseEntity.notFound().build(); */
    /*
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(){
        System.out.println("In exception handler");
        return  ResponseEntity.notFound().build();
    }*/

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateByPatchId(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer)
    {
        beerService.patchBeerById(beerId,beer);
        //We receive a request and everything happened normally.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
