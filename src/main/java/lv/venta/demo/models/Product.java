package lv.venta.demo.models;

public class Product {
    private int id;
    private String title;
    private String description;
    private int quantity;
    private float price;
    private static int counter = 1000;

    public int getId() {
        return id;
    }
    public void setId() {
        this.id = counter++;
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
        if (quantity > 0 && quantity < 100000) {
            this.quantity = quantity;
        } else {
            this.quantity = 1;
        }
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        if (price > 0) {
            this.price = price;
        } else {
            this.price = 10.0f;
        }
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
