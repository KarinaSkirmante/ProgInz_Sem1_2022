package lv.venta.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.demo.models.Product;
import lv.venta.demo.services.ProductCRUD;

//... - ārējais repo nosaukums
//git checkout master
//git pull ... master
//git merge kristians
//git push ... master

@Controller
public class MyFirstController {
	
	@Autowired
	private ProductCRUD productCRUDService;
		
	@GetMapping("/home") // url - localhost:8080/home
	public String getHomePage()
	{
		System.out.println("Mans home kontrolieris ir nostrādajis");
		return "home"; //parādīs home.html lapu
	}
	
	//izveidot get kontroleiru funkciju, kas nosūtītu ziņu uz frontend
	@GetMapping("/send") //url - localhost:8080/send
	public String getSend(Model model)
	{
		model.addAttribute("msg1", "Mana zina no backend uz frontend");
		model.addAttribute("msg2", "Karina");
		model.addAttribute("msg3", "Program. Inz.");
		return "msg-page";//parādōs msg-page.html
	}
	
	//izveidot get kontroleiru funkciju, kas nosūtītu objektu uz frontend
	@GetMapping("/object") //url - localhost:8080/object
	public String getObject(Model model)
	{
		Product prod = new Product("abols", "garsigs", 10, 0.99f);
		model.addAttribute("object", prod);
		return "one-product-page";//parādīsieis one-product-page.html
		
		
	}
	
	//1.uztaisīt kontrolējošo funkciju, kas pados allProducts uz frontend
	@GetMapping("/allProducts") //url - localhost:8080/allProducts
	public String getAllProducts(Model model)
	{
		model.addAttribute("object", productCRUDService.readAllProducts());
		return "all-product-page";//all-product-page.html
	}
	
	
	//localhost:8080/products?id=2 -> parāada tikai 2.produktu -> @RequestParam
	@GetMapping("/allProductsFilter") //url - localhost:8080/allProductsFilter?id=2
	public String getAllProductFilter(@RequestParam(name="id") int id , Model model)
	{
		try {
			model.addAttribute("object", productCRUDService.readProductById(id));
			return "one-product-page";//parādīsieis one-product-page.html
		}
		catch (Exception e) {
			return "error-page";
		}
		
	}
	
	
	//localhost:8080/products/2-> parāda tikai 2. produktu ->@PathVariable
	@GetMapping("/allProducts/{id}")
	public String getAllProductsById(@PathVariable(name="id") int id, Model model)
	{
		try {
			model.addAttribute("object", productCRUDService.readProductById(id));
			return "one-product-page";//parādīsieis one-product-page.html
		}
		catch (Exception e) {
			return "error-page";
		}
	}
	
	
	//1. getMapping kontroliera funkcija, kur nosūtītu tukšu objektu
	@GetMapping("/addProduct") //url-> localhost:8080/addProduct
	public String getAddProduct(Product product) //new Product() - tukšs
	{
		return "add-product-page"; //parādīs add-product-page.html un iedos product tuksu objektu 
	}
	
	//2. uztaisīt html lapu, kura var ievadīt visu produktu prametrus, izņemot id
	//3. postMapping funkcija, kas saņem aizpldīto objektu un saglabā sarakstā.
	
	@PostMapping("/addProduct")
	public String postAddProduct(Product product)//aizpildītais produkts
	{
		
		if(productCRUDService.createNewProduct(product))
			return "redirect:/allProducts";//post norda uz kuru url adresi pāŗlekt
		else
			return "redirect:/error";
			
	}
	//3.1 redirect uz /allProduct url

	
	@GetMapping("/updateProduct/{id}")//localhost:8080/updateProduct/2
	public String getUpdateProduct(@PathVariable(name="id") int id, Model model)
	{
		try {
			model.addAttribute("product", productCRUDService.readProductById(id));
			return "update-product-page";//parādīsieis update-product-page.html
		}
		catch (Exception e) {
			return "error-page";
		}
	}
	
	@PostMapping("/updateProduct/{id}")
	public String postUpdateProduct(@PathVariable(name="id") int id, Product product)
	{
		if(productCRUDService.updateProductById(id, product))
			return "redirect:/allProducts/"+id; //tiks izsaukt localhost:8080/allProducts/2
		else
			return "redirect:/error";//localhost:8080/error
	}
	
	//kontrolieris izsauk  error lapas parādīšanos
	@GetMapping("/error")//localhost:8080/error
	public String getError()
	{
		return "error-page";
	}
	
	//1. GetMapping deklarācija
	@GetMapping("/deleteProduct/{id}")
	//2. funkcijas deklarācija, pieliekam modeli
	public String getDeleteProduct(@PathVariable(name="id") int id, Model model)
	{
	//3. sameklējam konkrēto produktu pēc tā id
	//4. dzēsam to produktu
	for(Product temp: allProducts)
	{
		if(temp.getId()==id)
		{
			allProducts.remove(temp);
			//5. padodam visu produktu sarkstu caur modeli frontendam
			//6. paradām lapu all-product-page.html
			model.addAttribute("object", allProducts);
			return "all-product-page";//all-product-page.html
		}
	}
	return "error-page";
}
	
	@GetMapping("/filter")
	public String getFilter()
	{
		return "error-page";
	}
	
	
	
	
	
	
	
	
	

}
