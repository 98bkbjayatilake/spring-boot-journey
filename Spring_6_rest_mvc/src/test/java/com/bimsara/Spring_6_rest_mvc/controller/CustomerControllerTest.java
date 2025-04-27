package com.bimsara.Spring_6_rest_mvc.controller;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.model.Customer;
import com.bimsara.Spring_6_rest_mvc.services.CustomerService;
import com.bimsara.Spring_6_rest_mvc.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.core.Is.is;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<Customer> CustomerArgumentCaptor;

    @Test
    void testPatchCustomer() throws Exception{
        Customer testPatchCustomer = customerServiceImpl.listCustomers().get(0);

        Map<String,Object> customerMap=new HashMap<>();
        customerMap.put("customerName","New Name");//this mimics JSON like:{"beerName":"New Name"}

        mockMvc.perform(patch( CustomerController.CUSTOMER_PATH_ID, testPatchCustomer.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap))// convert Map->JSON String
                )
                .andExpect(status().isNoContent());

        verify(customerService).patchCustomerById(uuidArgumentCaptor.capture(),CustomerArgumentCaptor.capture());

        assertThat(testPatchCustomer.getCustomerId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("customerName")).isEqualTo(CustomerArgumentCaptor.getValue().getCustomerName());

    }

    @BeforeEach
    void  setup() throws  Exception{
        customerServiceImpl=new CustomerServiceImpl();
    }


    @Test
    void testDeleteBeer() throws Exception {
        Customer customer = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(delete( CustomerController.CUSTOMER_PATH_ID, customer.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        verify(customerService).deleteById(uuidArgumentCaptor.capture());
        assertThat(customer.getCustomerId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
    @Test
    void testUpdateCustomer() throws Exception{
        Customer testCustomer=customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(put( CustomerController.CUSTOMER_PATH_ID,testCustomer.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCustomer))
                )
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomerById(any(UUID.class),any(Customer.class));
    }
    @Test
    void testCreateNewCustomer() throws Exception {
        Customer customerToSave = customerServiceImpl.listCustomers().get(0);
        customerToSave.setCustomerId(null);
        customerToSave.setCustomerVersion(null);

        Customer savedCustomer=customerServiceImpl.listCustomers().get(1);

        given(customerService.saveNewCustomer(any(Customer.class))).willReturn(savedCustomer);

        mockMvc.perform(post( CustomerController.CUSTOMER_PATH).
                accept(MediaType.APPLICATION_JSON)
                  .contentType(MediaType.APPLICATION_JSON)
                   .content(objectMapper.writeValueAsString(customerToSave))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void listCustomers() throws Exception {
       given(customerService.listCustomers()).willReturn(customerServiceImpl.listCustomers());

       mockMvc.perform(get(CustomerController.CUSTOMER_PATH)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID,UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomerById() throws Exception {
        Customer testCustomer=customerServiceImpl.listCustomers().get(0);
        given(customerService.getCustomerById(testCustomer.getCustomerId())).willReturn(Optional.of(testCustomer));

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID,testCustomer.getCustomerId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.customerId",is(testCustomer.getCustomerId().toString())
                ))
                .andExpect(jsonPath(
                        "$.customerName",is(testCustomer.getCustomerName())
                ));
    }
}