package org.bimsara.Spring6_DI_App.controllers;

import org.bimsara.Spring6_DI_App.Services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetterInjectedControllerTest {

    SetterInjectedController setterInjectedController;

    @BeforeEach
    void setUp() {
     setterInjectedController=new SetterInjectedController();
     setterInjectedController.setGreetingService(new GreetingServiceImpl());
    }

    @Test
    void sayhello() {
        System.out.println(setterInjectedController.sayhello());
    }
}