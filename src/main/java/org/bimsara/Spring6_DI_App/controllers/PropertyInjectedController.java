package org.bimsara.Spring6_DI_App.controllers;


import org.bimsara.Spring6_DI_App.Services.GreetingService;

//Dependency Injection without spring framework (Dependency Injection Manually)
public class PropertyInjectedController {
    GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
