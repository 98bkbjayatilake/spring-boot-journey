package com.bimsara.Spring_6_rest_mvc.services;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        public Optional<Customer> getCustomerById(UUID customerId) {
            return  Optional.of(customerMap.get(customerId));
        }

        @Override
        public Customer saveNewCustomer(Customer newCustomer){
        Customer savedCustomer=Customer.builder()
                .customerName(newCustomer.getCustomerName())
                .customerId(UUID.randomUUID())
                .customerVersion(newCustomer.getCustomerVersion())
                .customerCreatedDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getCustomerId(),savedCustomer);

        return savedCustomer;
        }
    @Override
    public void updateCustomerById(UUID customerId,Customer updatedCustomer){
        Customer existingcustomer=customerMap.get(customerId);
        existingcustomer.setCustomerName(updatedCustomer.getCustomerName());
        existingcustomer.setCustomerVersion(updatedCustomer.getCustomerVersion());

        customerMap.put(existingcustomer.getCustomerId(),existingcustomer);
    }

    public void deleteById(UUID customerId){
        customerMap.remove(customerId);
    }
@Override
    public   void patchCustomerById(UUID customerId, Customer patchCustomer){
        Customer existingCustomer=customerMap.get(customerId);

        //check if the new name of existing customer is not null or  empty, they update it
        if(StringUtils.hasText(patchCustomer.getCustomerName())){
            existingCustomer.setCustomerName(patchCustomer.getCustomerName());
        }

        // Check if the new version of existing customer , then update it
        if (patchCustomer.getCustomerVersion() != null) {
            existingCustomer.setCustomerVersion(patchCustomer.getCustomerVersion());
        }

    }

}

