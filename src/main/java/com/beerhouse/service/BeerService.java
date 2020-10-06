package com.beerhouse.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.request.BeerRequest;
import com.beerhouse.response.BeerResponse;

@Service
public class BeerService {

    @Autowired
    BeerRepository beerRepository;
    
    private Beer asBeer(BeerRequest beerRequest) {
    	Beer beer = new Beer();
    	beer.setName(beerRequest.getName());
    	beer.setIngredients(beerRequest.getIngredients());
    	beer.setCategory(beerRequest.getCategory());
    	beer.setPrice(beerRequest.getPrice());
    	beer.setAlcoholContent(beerRequest.getAlcoholContent());
    	
    	return beer;		
    }
    
   public ResponseEntity<BeerResponse> createBeer(BeerRequest beerRequest){
	   Beer beer = beerRepository.save(asBeer(beerRequest));
	   return ResponseEntity.status(HttpStatus.CREATED).body(new BeerResponse(beer));
   }
   
   public ResponseEntity<List<BeerResponse>> listAll(){
	   return ResponseEntity.ok(StreamSupport.stream(beerRepository.findAll().spliterator(), false)
               .map(BeerResponse::new).collect(Collectors.toList()));
   }     
   
   public ResponseEntity<BeerResponse> findById(Long id){
	   Optional<Beer> optionalBeer =  Optional.ofNullable(beerRepository.findOne(id));
	   
	   if(!optionalBeer.isPresent()) {
		   return ResponseEntity.notFound().build();
       }
       
       return ResponseEntity.ok().body(new BeerResponse(optionalBeer.get()));
   }
   
   public ResponseEntity<BeerResponse> update(Long id, BeerRequest beerRequest){
	   
       if(!beerRepository.exists(id)) {
           return ResponseEntity.notFound().build();
       }
       
       BeerResponse beerResponse = new BeerResponse(beerRepository.save(new Beer(id, beerRequest.getName(), beerRequest.getIngredients(), 
    		   													   beerRequest.getAlcoholContent(), beerRequest.getCategory(), beerRequest.getPrice())));
       return ResponseEntity.ok().body(beerResponse);
   }
   
   public ResponseEntity patch(Long id, Map<Object, Object> fields){
	   try {
	       if(!beerRepository.exists(id)) {
	           return ResponseEntity.notFound().build();
	       }
	       
	       Beer beer = beerRepository.findOne(id);
	       
	       fields.forEach((k, v) -> {
	    	   
	            Field field = ReflectionUtils.findField(Beer.class, (String)k);
	            field.setAccessible(true);
	            ReflectionUtils.setField(field, beer, v);
	        });
	        
	       BeerResponse beerResponse = new BeerResponse(beerRepository.save(beer));
	       return ResponseEntity.ok().body(beerResponse);
       }catch(Exception e) {
    	   return ResponseEntity.badRequest().body(e.getMessage());
       }
   }
   
   public ResponseEntity<Beer> delete(Long id){
	   
       if(!beerRepository.exists(id)) {
           return ResponseEntity.notFound().build();
       }

	   beerRepository.delete(id);
       return ResponseEntity.noContent().build();

   }
}
