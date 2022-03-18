package com.example.demo.models;

public class Product {
	//id, title, description, quantum, price
	//getters, setters, constructors
	private int id;
	private static int idCounter = 1000;
	private String title;
	private String description;
	private int quantum;
	private float price;
	
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = idCounter;
		idCounter++;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		
	}
	
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Product(){
	}
	public Product(String title,String description,int quantum,float price){
		setId();
		setTitle(title);
		setDescription(description);
		setQuantum(quantum);
		setPrice(price);
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", quantum=" + quantum
				+ ", price=" + price + "]";
	}
}
