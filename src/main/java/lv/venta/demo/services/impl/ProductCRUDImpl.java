package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.demo.models.Product;
import lv.venta.demo.services.ProductCRUD;

@Service
public class ProductCRUDImpl implements ProductCRUD {

	private ArrayList<Product> allProducts = new ArrayList<>
	(Arrays.asList(new Product("abols", "garsigs", 10, 0.99f),
			new Product("bumbieris", "zals", 2, 0.12f), 
			new Product("arbuzs", "salds", 3, 1.99f)));

	
	@Override
	public boolean createNewProduct(String title, String description, int quantity, float price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Product> readAllProducts() {
		return allProducts;
	}

	@Override
	public Product readProductById(int id) throws Exception {
		for(Product temp: allProducts)
		{
			if(temp.getId()==id)
			{
				return temp;
			}
		}
		throw new Exception("Produkts neeksistē");
		
	}

	@Override
	public boolean updateProductById(int id, String title, String description, int quantity, float price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
