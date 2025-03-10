package org.bimsara.Spring6_DI_App.controllers;
//dependencyInjection without Spring

import org.bimsara.Spring6_DI_App.Services.GreetingService;

//via setter
public class SetterInjectedController {
    private GreetingService greetingService;


    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayhello(){
        return greetingService.sayGreeting();
    }
}
