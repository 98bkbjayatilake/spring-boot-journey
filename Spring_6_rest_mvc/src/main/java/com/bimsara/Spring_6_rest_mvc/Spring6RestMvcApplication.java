package com.bimsara.Spring_6_rest_mvc;

import com.bimsara.Spring_6_rest_mvc.controller.BeerController;
import com.bimsara.Spring_6_rest_mvc.controller.CustomerController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication(scanBasePackages = "com.bimsara.Spring_6_rest_mvc")
public class Spring6RestMvcApplication implements CommandLineRunner {

	@Autowired
	private BeerController beerController;
	@Autowired
	private CustomerController customerController;

	public static void main(String[] args) {
		SpringApplication.run(Spring6RestMvcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// This will invoke ListBeers method from BeerController
		System.out.println("Invoking ListBeers from BeerController...");
		beerController.ListBeers();
		System.out.println("Invoking listCustomers from CustomerController...");
		customerController.listCustomers();

	}
}
