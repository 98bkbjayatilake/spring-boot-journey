package org.bimsara.Spring6_DI_App.controllers;

import org.bimsara.Spring6_DI_App.Services.GreetingService;

//Dependency Injection without spring
public class ConstructorInjectedController {
    private final GreetingService greetingService;

    public ConstructorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
