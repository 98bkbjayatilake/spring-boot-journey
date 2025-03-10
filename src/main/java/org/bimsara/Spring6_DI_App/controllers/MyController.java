package org.bimsara.Spring6_DI_App.controllers;

import org.bimsara.Spring6_DI_App.Services.GreetingService;
import org.bimsara.Spring6_DI_App.Services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private  final GreetingService greetingService;

    /*no dependency injection
    *just using the normal new keyword
    * Dependencies Without Injection
    * intializing the greeting service internally.
    */
    public  MyController(){
        this.greetingService=new GreetingServiceImpl();
    }
    /*public String sayhello (){
        System.out.println("I'm in the controller");

        return "Hello Everyone!!!";
    }*/

    public String sayhello (){
        System.out.println("I'm in the controller");

        return greetingService.sayGreeting();
    }
}
