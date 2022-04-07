package lv.venta.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name="product_table")
@Entity
public class Product {
    
	@Column(name="Id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
	@Column(name="Title")
    @Size(min=4, max=20)
    @Pattern(regexp="[A-Z]{1}[a-z]+")
    private String title;
    
	@Column(name="Description")
    @Size(min=5, max=20)
    @Pattern(regexp="[A-Z]{1}[a-z\\s]+")
    private String description;

	@Column(name="Quantity")
    @Min(value=1)
    @Max(value=1000)
	private int quantity;
    
	@Column(name="Price")
    @Max(value=10000)
	@Min(value=0)
    private float price;
    
    public int getId() {
        return id;
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

