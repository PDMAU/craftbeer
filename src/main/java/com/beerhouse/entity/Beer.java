package com.beerhouse.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity(name = "beer")
public class Beer {
	
	@Id 
	@SequenceGenerator(name = "beerIdGenerator", sequenceName = "beer_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beerIdGenerator")
	private Long id;
	
	private String name;
	
	private String ingredients;	
	
	private String alcoholContent;
	
	private Double price;
	
	private String category;
	
	public Beer() {
		super();
	}
	
	public Beer(Long id, String name, String ingredients, String alcoholContent, String category, Double price) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.category = category;
		this.price  =	price;
		this.alcoholContent = alcoholContent;
		
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
	

}
 