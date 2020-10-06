package com.beerhouse.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.beerhouse.entity.Beer;
import com.beerhouse.mock.BeerMock;
import com.beerhouse.request.BeerRequest;
import com.beerhouse.response.BeerResponse;
import com.beerhouse.service.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class)

@WebMvcTest(BeerController.class)

public class BeerControllerTests {
	@Autowired
    private MockMvc mvc;

	@MockBean
    private BeerService beerService;	
	
	@Test
    public void createBeerSucess() throws Exception {
        BeerRequest beerRequest = BeerMock.validBeerRequest();
        BeerResponse beerResponse = BeerMock.validBeerResponse();
        when(beerService.createBeer(beerRequest)).thenReturn(ResponseEntity.status(201).body(beerResponse));
               
        mvc.perform(MockMvcRequestBuilders.post("/beers")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(BeerMock.convertToJSON(beerRequest))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
	
	@Test
    public void listAllSucess() throws Exception {
        when(beerService.listAll()).thenReturn(ResponseEntity.status(200).body(BeerMock.validBeerResponseList()));
               
        String responseBody  = mvc.perform(MockMvcRequestBuilders.get("/beers")
                .accept(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(BeerMock.convertToJSON(BeerMock.validBeerResponseList()).equals(responseBody));
    }
	
	@Test
	public void deleteWithInvalidIdReturnNotFound() throws Exception{
        when(beerService.delete(1L)).thenReturn(ResponseEntity.notFound().build());
        mvc.perform(MockMvcRequestBuilders.delete("/beers/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteWithValidIdReturnSucess() throws Exception{
        when(beerService.delete(1L)).thenReturn(ResponseEntity.noContent().build());
        mvc.perform(MockMvcRequestBuilders.delete("/beers/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
	}
	
	@Test
	public void findByIdWithInvalidIdReturnNotFound() throws Exception{
        when(beerService.findById(1L)).thenReturn(ResponseEntity.notFound().build());
        String bodyResponse = mvc.perform(MockMvcRequestBuilders.get("/beers/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
        assertTrue(StringUtils.isEmpty(bodyResponse));
	}
	
	@Test
	public void findByIdWithValidIdReturnSucess() throws Exception{
        when(beerService.findById(1L)).thenReturn(ResponseEntity.ok().body(BeerMock.validBeerResponse()));
        String bodyResponse = mvc.perform(MockMvcRequestBuilders.get("/beers/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(BeerMock.convertToJSON(BeerMock.validBeerResponse()).equals(bodyResponse));
	}

	
	@Test
	public void updateWithInvalidIdReturnNotFound() throws Exception{
        when(beerService.update(isA(Long.class), isA(BeerRequest.class))).thenReturn(ResponseEntity.notFound().build());
        mvc.perform(MockMvcRequestBuilders.put("/beers/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(BeerMock.convertToJSON(BeerMock.validBeerRequest()))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
	}
	
	@Test
	public void updateWithValidIdReturnSucess() throws Exception{
        when(beerService.update(isA(Long.class), isA(BeerRequest.class))).thenReturn(ResponseEntity.ok().body(BeerMock.validBeerResponse()));
        String bodyResponse = mvc.perform(MockMvcRequestBuilders.put("/beers/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(BeerMock.convertToJSON(BeerMock.validBeerRequest()))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(BeerMock.convertToJSON(BeerMock.validBeerResponse()).equals(bodyResponse));

	}
	
	
	
	
}
