package com.bimsara.Spring_6_rest_mvc.controller;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //POST is a create request
    @PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer){
        Beer saveBeer=beerService.saveNewBeer(beer);

        HttpHeaders headers=new HttpHeaders();
        headers.add("Location","/api/v1/beer/" +saveBeer.getId().toString());
        return  new ResponseEntity(headers,HttpStatus.CREATED);
    }

    //PUT is used to update a resource
    @PutMapping("{beerId}")
    public ResponseEntity updateById(
            @PathVariable("beerId") UUID beerId,
            @RequestBody Beer beer)
    {
      beerService.updateBeerById(beerId,beer);
      //We receive a request and everything happened normally.
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@PathVariable ("beerId") UUID beerId){
        beerService.deleteById(beerId);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{beerId}")
    public ResponseEntity updateByPatchId(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer)
    {
        beerService.patchBeerById(beerId,beer);
        //We receive a request and everything happened normally.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
