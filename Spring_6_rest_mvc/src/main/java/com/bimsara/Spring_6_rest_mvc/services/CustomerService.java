package com.bimsara.Spring_6_rest_mvc.services;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.model.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCustomerById(UUID customerId);

    Customer saveNewCustomer(Customer newCustomer);
    void updateCustomerById(UUID customerId,Customer updatedCustomer);

    void deleteById(UUID customerId);

    void patchCustomerById(UUID customerId,Customer patchCustomer);
}
