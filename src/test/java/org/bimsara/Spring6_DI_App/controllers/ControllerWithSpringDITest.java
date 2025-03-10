package org.bimsara.Spring6_DI_App.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ControllerWithSpringDITest {

    @Autowired
    ControllerWithSpringDI controllerWithSpringDI;

    @Test
    void sayHello() {
        System.out.println(controllerWithSpringDI.sayHello());
    }
}