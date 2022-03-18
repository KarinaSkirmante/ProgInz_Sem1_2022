package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;

@Controller
public class MyFirstController {

	
	private ArrayList<Product> allProducts = new ArrayList<>
		(Arrays.asList(	new Product("Abols","Garsigs, Latvija audzets",10,0.59f),
					   	new Product("Gurkis","Garsigs, Latvija audzets",12,0.75f)));
	
	@GetMapping("/home")// url - localhost:8080/home
	public String getHomePage() {
		
		System.out.println("Mans home kontrolieris strada");
		return "home"; // paradis home.html lappu
	}
	
	@GetMapping("/send")// url - localhost:8080/send
	public String getSend(Model model) {
		model.addAttribute("msg1", "Mana zina no back end to front");
		model.addAttribute("msg2", "(:");
		return "msg-page";
	}
	// Nosuta objektu uz frontend
	@GetMapping("/product")// url - localhost:8080/product
	public String getProduct(Model model) {
		Product prod = new Product("Abols","Garsigs, Latvija audzets",10,0.99f);
		model.addAttribute("object", prod);
		return "one-product-page";
	}
	
	@GetMapping("/allProducts") // url - localhost:8080/allProducts
	public String getAllProducts(Model model) {
		model.addAttribute("object",allProducts);
		return "all-product-page";
	}
	
	
	//localhost:8080/producs?id=1 --> tiek paradits 1.produkts 
	@GetMapping("/allProductsFilter") // url - localhost:8080/allProductsFilter
	public String getAllProductsFilter(@RequestParam(name="id") int id,Model model) {	
		for(Product temp: allProducts) {
			if(temp.getId() == id) {
				model.addAttribute("object",temp);
				return "one-product-page";
			}
		}
		return "error-page";
	}
	
	@GetMapping("/allProducts/{id}") // url - localhost:8080/allProducts/{id}
	public String getAllProductsById(@PathVariable(name = "id")int id, Model model) {
		for(Product temp: allProducts) {
			if(temp.getId() == id) {
				model.addAttribute("object",temp);
				return "one-product-page";
			}
		}
		return "error-page";
	}
	//1. getMapping controliera funkcija nosuta tuksu objektu
	
	@GetMapping("/addProduct") // url - localhost:8080/addProduct
	public String getAddProduct(Product product) { 		// tuks produkts
		return "add-product-page";
	}
	
	//2. Uztaisit html kur var ievadit visus produkta parametrus
	//3. postMapping funkicja kas sanem atbildi no objekta un saglaba saraksta.
	//	 redirect uz /allProducts url

	@PostMapping("/addProduct")
	public String postAddProduct(Product product) { 	// aizpidlitais produkts
		Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getQuantum(), product.getPrice());
		allProducts.add(newProduct);
		return "redirect:/allProducts"; // Pie post norada uz kuru addresi pārlekt 
	}
	
	@GetMapping("/updateProduct/{id}") //localhost:8080/updateProduct/*
	public String getUpdateProduct(@PathVariable(name="id") int id, Model model) {
		for (Product temp: allProducts) {
			if (temp.getId()==id) {
				model.addAttribute("product", temp);
				return "update-product-page";
			}
		}
		
		return "error-page";
	}
	
	@PostMapping("/updateProduct/{id}")
	public String postUpdateProdcut(@PathVariable(name="id") int id, Product product) {
		for (Product temp: allProducts) {
			if (temp.getId()==id) {
				temp.setTitle(product.getTitle());
				temp.setDescription(product.getDescription());
				temp.setPrice(product.getPrice());
				temp.setQuantum(product.getQuantum());
				return "redirect:/allProducts/"+id; // lh:8080/allProducts/{id}
			}
		}
		
		return "redirect:/error"; // <-- Post izmantot redirect
	}
	
	@GetMapping("/error")
	public String getError() {
		return "error-page"; 
	}
	
	//	DELETE FUNKCIJA
		//1. GetMapping dekleracija
		//2. funkcijas keleracija, pielikt model
		//3. sameklejam konkreto produktu pēc tā id
		//4. dzēsam to produktu
		//5. padodam visu produktu sarakstu caur modeli forntendam
		//6. paradam lapu all-product-page.html
	
	@GetMapping("/deleteProduct/{id}")
	public String getDeleteProduct(@PathVariable(name="id") int id, Model model) {
		for (Product temp: allProducts) {
			if (temp.getId()==id) {
				allProducts.remove(temp);
				model.addAttribute("product", allProducts);
				return "all-product-page";
			}
		}
		return "error-page";
	}
}