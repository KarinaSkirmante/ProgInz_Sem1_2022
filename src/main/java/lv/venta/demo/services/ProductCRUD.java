package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Product;

public interface ProductCRUD {
	//CRUD
	//C  - create
	public abstract boolean createNewProduct(Product product);
	
	//R  - read or retrieve  -all
	public abstract ArrayList<Product> readAllProducts();
	
	//R  - read or retrieve  - one
	public abstract Product readProductById(int id) throws Exception ;
	
	//U - update
	public abstract boolean updateProductById(int id, String title, 
			String description, int quantity, float price );
	
	//D - delete
	public abstract boolean deleteProductById(int id);
	

}
