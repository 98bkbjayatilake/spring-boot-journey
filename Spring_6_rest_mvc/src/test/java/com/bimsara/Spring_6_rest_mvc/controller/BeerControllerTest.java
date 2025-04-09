package com.bimsara.Spring_6_rest_mvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerControllerTest {
    @Autowired
    BeerController beerController;
    //here you can see on the test pretty common that you're going to use public properties on test
    @Test
    void getBeerById() {
        System.out.println(beerController.getBeerById(UUID.randomUUID()));

    }
}