package com.bimsara.Spring_6_rest_mvc.services;

import com.bimsara.Spring_6_rest_mvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> listBeers();
    Beer getBeerById(UUID customerId);
}
