package lv.venta.demo.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Product {
    private int id;
    
    @Size(min=4, max=20)
    @Pattern(regexp="[A-Z]{1}[a-z]+")
    private String title;
    
    @Size(min=5, max=20)
    @Pattern(regexp="[A-Z]{1}[a-z\\s]+")
    private String description;

    @Min(value=1)
    @Max(value=1000)
	int quantity;
    private float price;
    
    private static int counter = 1000;

    public int getId() {
        return id;
    }
    public void setId() {
        this.id = counter;
        counter++;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public Product() {
    }
    
    public Product(String title, String description, int quantity, float price) {
    	setId();
    	setTitle(title);
    	setDescription(description);
    	setQuantity(quantity);
    	setPrice(price);
    }
    @Override
    public String toString() {
        return "Product [description=" + description + ", id=" + id + ", price=" + price + ", quantity=" + quantity
                + ", title=" + title + "]";
    }

}

