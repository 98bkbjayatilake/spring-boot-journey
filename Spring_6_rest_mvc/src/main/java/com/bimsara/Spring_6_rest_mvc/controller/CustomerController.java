package com.bimsara.Spring_6_rest_mvc.controller;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.model.Customer;
import com.bimsara.Spring_6_rest_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }
    @RequestMapping(value = "{customerId}",method = RequestMethod.GET)
    public Customer getCustomerById(
            @PathVariable("customerId") UUID Id
            )
    {
        return customerService.getCustomerById(Id);
    }

    //POST is a create request
   @PostMapping
    public ResponseEntity handlePost(@RequestBody  Customer newCustomer){
        Customer savedCustomer=customerService.saveNewCustomer(newCustomer);
        HttpHeaders customerHeader=new HttpHeaders();
        customerHeader.add("Location","/api/v1/customer" +savedCustomer.getCustomerId().toString());
        return  new ResponseEntity<>(savedCustomer,customerHeader, HttpStatus.CREATED);
   }

   @PutMapping("{customerId}")
   public ResponseEntity updateCustomerById(
           @PathVariable("customerId") UUID customerId,
           @RequestBody Customer updatedCustomer
   ){
        customerService.updateCustomerById(customerId,updatedCustomer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @DeleteMapping("{customerId}")
   public  ResponseEntity deleteById(@PathVariable("customerId") UUID Id){
        customerService.deleteById(Id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

    @PatchMapping("{customerId}")
    public ResponseEntity updateByPatchId(@PathVariable("customerId") UUID Id, @RequestBody Customer patchCustomer)
    {
        customerService.patchCustomerById(Id,patchCustomer);
        //We receive a request and everything happened normally.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
