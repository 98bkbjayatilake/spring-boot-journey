package com.bimsara.Spring_6_rest_mvc.controller;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.services.BeerService;
import com.bimsara.Spring_6_rest_mvc.services.BeerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest

//@WebMvcTest is a Spring boot test splice which creates a MockMvc environment for the controller(or controllers) under test.
//@WebMvcTest(BeerControllerTest)-specifically limit to the BeerController class
//@WebMvcTest(BeerController.class)

@WebMvcTest (BeerController.class)

class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<Beer> beerArgumentCaptor;

    @BeforeEach
    void setUp() {
        // Resets the beerServiceImpl with a fresh instance before each test
        // This ensures isolation and avoids shared state between tests
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void testPatchBeer() throws Exception {
        Beer testPatchBeer = beerServiceImpl.listBeers().get(0);

        //Create an ad-hoc JSON payload using a Map
        //We're simulating a client PATCH request that only wants to update the 'beerName' field
        //Instead of sending a fully populated Beer object ,we only send the field we want to change.
        Map<String,Object> beerMap=new HashMap<>();
        beerMap.put("beerName","New Name");//this mimics JSON like:{"beerName":"New Name"}

        mockMvc.perform(patch(BeerController.BEER_PATH_ID, testPatchBeer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap))// convert Map->JSON String
        )
                .andExpect(status().isNoContent());

        verify(beerService).patchBeerById(uuidArgumentCaptor.capture(),beerArgumentCaptor.capture());

        assertThat(testPatchBeer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
    }

    @Test
    void testDeleteBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(delete(BeerController.BEER_PATH_ID, beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        //to capture the UUID passed to the deleteById() method
        //This allows us to inspect what was actually sent to the service mock.
        //ArgumentCaptor<UUID> uuidArgumentCaptor= ArgumentCaptor.forClass(UUID.class);

        /*Verify that the deleteById() method on the mock BeerService was called, and capture the argument it was called with.*/
        verify(beerService).deleteById(uuidArgumentCaptor.capture());

        /*Assert that the UUID captured from the mock method call matches the ID of the Beer object we originally used.
         This ensures the controller correctly parsed and forwarded the path variable.*/
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

        @Test
    void testUpdateBeer() throws Exception{
        Beer beer=beerServiceImpl.listBeers().get(0);

        mockMvc.perform(put(BeerController.BEER_PATH_ID,beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer))
        )
                .andExpect(status().isNoContent());

        //verify(beerService)-This tells Mockito"Watch this mock(beerService)and verify that one of its methods was called.

        //.updateBeerById(...)-this is exact method you are verifying.

        //It checks that: updateBeerById() was called exactly once
        verify(beerService).updateBeerById(any(UUID.class),any(Beer.class));
    }

    @Test
    void testCreateNewBeer() throws Exception {
        //ObjectMapper is a class from the Jackson  library  used to convert Java objects to JSON and vice versa
       //ObjectMapper objectMapper=new ObjectMapper();
       //this line tells the objectMapper to find and register all available modules
       //objectMapper.findAndRegisterModules();
      // Beer beer=beerServiceImpl.listBeers().get(0);
       //converts the  Beer object to a JSON string using Jackson
       //System.out.println(objectMapper.writeValueAsString(beer));

        // Prepare a beer object to send in the POST request (mimics a client-sent object)
        Beer beerToSave = beerServiceImpl.listBeers().get(0);
        beerToSave.setId(null);
        beerToSave.setVersion(null);

        // Prepare the beer that we want the mock service to return when saving
        // This mimics what the controller would return after creating a beer
        Beer savedBeer = beerServiceImpl.listBeers().get(1);

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(savedBeer);

        mockMvc.perform(
                post(BeerController.BEER_PATH)
                        .accept(MediaType.APPLICATION_JSON)// What response format we expect
                        .contentType(MediaType.APPLICATION_JSON) // What format we're sending
                         .content(objectMapper.writeValueAsString(beerToSave))// JSON body
                )
                .andExpect(status().isCreated()) // Expecting 201 Created status
                .andExpect(header().exists("Location")); // Expect a Location header in the response
    }
    @Test
    void testListBeers() throws Exception {
       given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers( ));

       mockMvc.perform(
               get(BeerController.BEER_PATH)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getBeerByIdNotFound() throws Exception {
        //Mock behavior:when beerService.getBeerById() is called with ANY  UUID,
        //it will throw a NotFoundException
        given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());

        // Perform a GET request to BeerController.BEER_PATH_ID with a random UUID.
        mockMvc.perform(get(BeerController.BEER_PATH_ID,UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
    @Test
    void getBeerById() throws Exception {
        //just grab the first one that's being initialized
        Beer testBeer=beerServiceImpl.listBeers().get(0);

        //Define the expected behaviour of the mocked bookService.
        //when the getBeerById() is called with the testBeer ID, return testBeer
        given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

        //Simulate a GET request to the endpoint "/api/v1/beer/{id}"
        mockMvc.perform(get(BeerController.BEER_PATH_ID, testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON))//Tell the controller we expect JSON in the response
                .andExpect(status().isOk())//verify that the response status is 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))//verify that the response content is JSON
                // Verify that the response JSON contains the correct ID
                .andExpect(jsonPath("$.id",is(testBeer.getId().toString())))
                // Verify that the response JSON contains the correct beer name
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));
    }
}