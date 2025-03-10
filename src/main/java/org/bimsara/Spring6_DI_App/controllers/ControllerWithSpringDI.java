package org.bimsara.Spring6_DI_App.controllers;

import org.bimsara.Spring6_DI_App.Services.GreetingService;
import org.bimsara.Spring6_DI_App.Services.GreetingServiceImplWithDISpring;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerWithSpringDI {

    private final  GreetingServiceImplWithDISpring  greetingServiceImplWithDISpring;

    public ControllerWithSpringDI(GreetingServiceImplWithDISpring greetingServiceImplWithDISpring){
        this.greetingServiceImplWithDISpring=greetingServiceImplWithDISpring;
    }

    public String sayHello(){
        return  greetingServiceImplWithDISpring.sayGreeting();
    }
}
