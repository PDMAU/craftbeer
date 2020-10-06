package com.beerhouse.response;

import java.util.Objects;

import com.beerhouse.entity.Beer;

public class BeerResponse {
	
	private Long id;
	
	private String name;
	
	private String ingredients;	
	
	private String alcoholContent;
	
	private Double price;
	
	private String category;
	
	public BeerResponse() {
		super();
	}
	
	public BeerResponse(Beer beer) {
		this.setId(beer.getId());
		this.setName(beer.getName());
		this.setIngredients(beer.getIngredients());
		this.setCategory(beer.getCategory());
		this.setPrice(beer.getPrice());
		this.setAlcoholContent(beer.getAlcoholContent());
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(String alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public boolean equals(Object o) {		
		if (this == o) { 
			return true;
		}
		
        if (o == null || getClass() != o.getClass()) { 
        	return false;
        }
        BeerResponse that = (BeerResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.getName());
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.ingredients);
    }

	
	

}
