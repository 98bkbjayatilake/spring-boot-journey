package org.bimsara.Spring6_DI_App.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyControllerTest {

    @Test
    void sayhello() {
        MyController myController=new MyController();
        System.out.println(myController.sayhello());

    }
}