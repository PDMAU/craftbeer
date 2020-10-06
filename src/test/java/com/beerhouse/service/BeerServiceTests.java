package com.beerhouse.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;



import com.beerhouse.entity.Beer;
import com.beerhouse.mock.BeerMock;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.request.BeerRequest;
import com.beerhouse.response.BeerResponse;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BeerService.class })
public class BeerServiceTests {
	
	@MockBean
    private BeerRepository beerRepository;
	
	
    @Autowired
    private BeerService beerService;

	@Test
    public void createUser() {
         BeerRequest beerRequest = BeerMock.validBeerRequest();
 		 Beer beer = BeerMock.validBeer();
         when(beerRepository.save(isA(Beer.class))).thenReturn(beer);
         ResponseEntity<BeerResponse> beerResponseEntity = beerService.createBeer(beerRequest);
         assertEquals(201, beerResponseEntity.getStatusCodeValue());
    }
	
	@Test
	public void listAllUsers() {
		List<Beer> beerList = BeerMock.validBeerList();
        when(beerRepository.findAll()).thenReturn(beerList);
		List<BeerResponse> beerResponseList = BeerMock.validBeerResponseList();

        ResponseEntity<List<BeerResponse>> beerResponseEntity = beerService.listAll();
        assertEquals(200, beerResponseEntity.getStatusCodeValue());
        assertEquals(beerResponseList, beerResponseEntity.getBody());
	}
	
	@Test
	public void findByIdWithInvalidIdReturnNotFound() {		
        when(beerRepository.findOne(1L)).thenReturn(null);
        ResponseEntity<BeerResponse> beerResponseEntity = beerService.findById(1L);
        assertEquals(404, beerResponseEntity.getStatusCodeValue());
        assertEquals(null, beerResponseEntity.getBody());


	}
	
	@Test
	public void findByIdWithValidIdReturnValidBeerResponse() {
		Beer beer = BeerMock.validBeer();
		BeerResponse beerResponse = BeerMock.validBeerResponse();
		
        when(beerRepository.findOne(beer.getId())).thenReturn(beer);
        
        ResponseEntity<BeerResponse> beerResponseEntity = beerService.findById(beer.getId());
        assertEquals(200, beerResponseEntity.getStatusCodeValue());
        assertEquals(beerResponse, beerResponseEntity.getBody());

	}
	
	@Test
	public void deleteWithInvalidIdReturnNotFound() {
		when(beerRepository.exists(1L)).thenReturn(false);
        ResponseEntity<Beer> beerResponseEntity = beerService.delete(1L);
        assertEquals(404, beerResponseEntity.getStatusCodeValue());
        assertEquals(null, beerResponseEntity.getBody());
	}
	
	@Test
	public void deleteWithValidIdReturnSucess() {
		when(beerRepository.exists(1L)).thenReturn(true);
        ResponseEntity<Beer> beerResponseEntity = beerService.delete(1L);
        assertEquals(204, beerResponseEntity.getStatusCodeValue());
        assertEquals(null, beerResponseEntity.getBody());
	}
	
	@Test
	public void updateWithInvalidIdReturnNotFound() {
		
        BeerRequest beerRequest = BeerMock.validBeerRequest();

		when(beerRepository.exists(beerRequest.getId())).thenReturn(false);
		
        ResponseEntity<BeerResponse> beerResponseEntity = beerService.update(beerRequest.getId(), beerRequest);
        assertEquals(404, beerResponseEntity.getStatusCodeValue());
        assertEquals(null, beerResponseEntity.getBody());
	}
	
	@Test
	public void updateWithValidIdReturnSucess() {
		Beer beer = BeerMock.validBeer();
		BeerResponse beerResponse = BeerMock.validBeerResponse();
        BeerRequest beerRequest = BeerMock.validBeerRequest();

		when(beerRepository.exists(beerRequest.getId())).thenReturn(true);
        when(beerRepository.save(isA(Beer.class))).thenReturn(beer);
		
        ResponseEntity<BeerResponse> beerResponseEntity = beerService.update(beerRequest.getId(), beerRequest);
        assertEquals(200, beerResponseEntity.getStatusCodeValue());
        assertEquals(beerResponse, beerResponseEntity.getBody());
	}

	
}
