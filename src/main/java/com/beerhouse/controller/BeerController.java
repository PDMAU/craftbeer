package com.beerhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.beerhouse.entity.Beer;
import com.beerhouse.request.BeerRequest;
import com.beerhouse.response.BeerResponse;
import com.beerhouse.service.BeerService;

@RestController
public class BeerController {
	
    @Autowired
    private BeerService beerService;
    
    @PostMapping(value = "/beers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BeerResponse> createUser(@RequestBody BeerRequest beerRequest) {
        return beerService.createBeer(beerRequest);
    }

    @GetMapping(value = "/beers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BeerResponse>> beers() {
        return beerService.listAll();
    }
    
    @DeleteMapping(value = "/beers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Beer> deleteBeer(@PathVariable Long id) {
        return beerService.delete(id);
    }

    @GetMapping(value = "/beers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BeerResponse> findById(@PathVariable Long id) {
        return beerService.findById(id);
    }
    
    @PutMapping(value = "/beers/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BeerResponse> beers(@PathVariable Long id, @RequestBody BeerRequest beerRequest) {
        return beerService.update(id, beerRequest);
    }
    
    @PatchMapping(value = "/beers/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity beers(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        return beerService.patch(id, fields);
    }


    


}
