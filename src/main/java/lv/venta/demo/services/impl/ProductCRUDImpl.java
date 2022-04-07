package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Product;
import lv.venta.demo.repos.IProductRepo;
import lv.venta.demo.services.ProductCRUD;

@Service
public class ProductCRUDImpl implements ProductCRUD {

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public boolean createNewProduct(Product product) {
		if(productRepo.existsByTitleAndDescription(product.getTitle(), product.getDescription()))
		{
			return false;
		}
		else
		{
			productRepo.save(product);
			return true;
		}
	}

	@Override
	public ArrayList<Product> readAllProducts() {
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product readProductById(int id) throws Exception {
		
		if(productRepo.existsById(id))
		{
			Product prod = productRepo.findById(id).get();
			return prod;
		}
		
		
		throw new Exception("Produkts neeksistē");
		
	}

	@Override
	public boolean updateProductById(int id, Product product) {
		if(productRepo.existsById(id))
		{
				Product prod = productRepo.findById(id).get();
				prod.setTitle(product.getTitle());
				prod.setDescription(product.getDescription());
				prod.setPrice(product.getPrice());
				prod.setQuantity(product.getQuantity());
				productRepo.save(prod);
				return true;
						
		}
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		//3. sameklējam konkrēto produktu pēc tā id
		//4. dzēsam to produktu
		if(productRepo.existsById(id))
		{
			productRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
