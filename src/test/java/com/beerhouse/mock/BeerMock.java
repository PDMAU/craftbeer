package com.beerhouse.mock;


import java.util.ArrayList;
import java.util.List;

import com.beerhouse.entity.Beer;
import com.beerhouse.request.BeerRequest;
import com.beerhouse.response.BeerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract  class BeerMock {
	
	public static BeerRequest validBeerRequest() {
		
		BeerRequest beerRequest = new BeerRequest();
		beerRequest.setId(1L);
		beerRequest.setName("BeerMockRequest");
		beerRequest.setAlcoholContent("10%");
		beerRequest.setCategory("Preta");
		beerRequest.setIngredients("[Cevada, Trigo, Canela]");
		beerRequest.setPrice(10.0); 
		
        return beerRequest;
    }

    public static Beer validBeer() {
    	Beer beer = new Beer();
    	beer.setId(1L);
    	beer.setName("BeerMockRequest");
    	beer.setAlcoholContent("10%");
    	beer.setCategory("Preta");
    	beer.setIngredients("[Cevada, Trigo, Canela]");
    	beer.setPrice(10.0); 
		
        return beer;
    }

    public static BeerResponse validBeerResponse() {    	
        return new BeerResponse(validBeer());
    }
    
    public static List<BeerResponse> validBeerResponseList(){
    	List<BeerResponse> list = new ArrayList<BeerResponse>();
    	list.add(validBeerResponse());
    	return list;
    }
    
    public static List<Beer> validBeerList(){
    	List<Beer> list = new ArrayList<Beer>();
    	list.add(validBeer());
    	
    	return list;
    }
    
    public static String convertToJSON(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();        
        return mapper.writeValueAsString(object);
    }



}
