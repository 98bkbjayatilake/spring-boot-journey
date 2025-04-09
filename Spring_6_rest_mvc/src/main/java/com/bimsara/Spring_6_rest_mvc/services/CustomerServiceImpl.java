package com.bimsara.Spring_6_rest_mvc.services;

import com.bimsara.Spring_6_rest_mvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class CustomerServiceImpl implements CustomerService {

    //type of the Key-UUID
    //type of the Value-Customer
    private Map<UUID, Customer> customerMap;

    public  CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .customerName("Bimsara Jayatilake")
                .customerId(UUID.randomUUID())
                .customerVersion(2)
                .customerCreatedDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .customerName("John Smith")
                .customerId(UUID.randomUUID())
                .customerVersion(2)
                .customerCreatedDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .customerName("Kevin Peterson")
                .customerId(UUID.randomUUID())
                .customerVersion(2)
                .customerCreatedDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getCustomerId(), customer1);
        customerMap.put(customer2.getCustomerId(), customer2);
        customerMap.put(customer3.getCustomerId(), customer3);
    }
    @Override
        public List<Customer> listCustomers() {
            return new ArrayList<>(customerMap.values());
        }
    @Override
        public Customer getCustomerById(UUID customerId) {
            return customerMap.get(customerId);
        }

}

