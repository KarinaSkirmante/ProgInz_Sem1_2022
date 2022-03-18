package lv.venta.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lv.venta.demo.models.Product;

@Controller
public class MyFirstController2 {

    private ArrayList<Product> allProducts = new ArrayList<>
        (Arrays.asList(new Product("abols", "garsigs", 10, 0.99f),
                        new Product("bumbieris", "zals", 2, 0.12f), 
                        new Product("arbuzs", "salds", 3, 1.99f)));

    // URL - localhost:8080/home
    @GetMapping("/home")
    public String getHomePage() {
        System.out.println("Mans kontrolieris ir nostradajis!");
        return "home"; // parādīs home.html lapu
    }

    @GetMapping("/send")
    public String getSend(Model model) {
        System.out.println("Mans kontrolieris ir nostradajis!");
        model.addAttribute("message", "Test");
        return "message"; // parādīs home.html lapu
    }

    @GetMapping("/object")
    public String getObject(Model model)
    {

        Product prod = new Product("abols", "garsigs", 10, 0.99f);
        model.addAttribute("product", prod);
        return "one-product-page";
    }

    @GetMapping("/allProducts")
    public String getAllProducts(Model model)
    {
        model.addAttribute("product", allProducts);
        return "many-product-page";
    }

    @GetMapping("/products")
    public String getProduct(@RequestParam int id, Model model) {
        for (Product p : allProducts) {
            if (p.getId() == id) {
                model.addAttribute("product", p);
                return "one-product-page";
            }
        }
        return "error";
    }

    @GetMapping("/products/{id}")
    public String getProductPath(@PathVariable int id, Model model) {
        for (Product p : allProducts) {
            if (p.getId() == id) {
                model.addAttribute("product", p);
                return "one-product-page";
            }
        }
        return "error";
    }

    @GetMapping("/addProduct")
    public String getAddProduct(Product product)
    {
        return "new-product";
    }

    @PostMapping("/addProduct")
    public String postAddProduct(Product product)
    {
        Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getQuantity(), product.getPrice());
        allProducts.add(newProduct);

        return "redirect:/allProducts";
    }

    @GetMapping("/updateProduct/{id}")
    public String getUpdateProduct(@PathVariable(name="id") int id, Model model) {

        for (Product p : allProducts) {
            if (p.getId() == id) {
                model.addAttribute("product", p);
                return "update-product-page";
            }
        }

        return "error";
    }

    @PostMapping("/updateProduct/{id}")
    public String postUpdateProduct(@PathVariable(name="id") int id, Product product)
    {
        for(Product temp: allProducts) {
            if (temp.getId() == id) {
                temp.setTitle(product.getTitle());
                temp.setDescription(product.getDescription());
                temp.setPrice(product.getPrice());
                temp.setQuantity(product.getQuantity());
                return "redirect:/products/" + id;
            }
        }

        return "redirect:/error";
    }

    // Error page
    @GetMapping("/error")
    public String getError() {
        return "error";
    }

    // Delete product
    @GetMapping("/deleteProduct/{id}")
    public String getDeleteProduct(@PathVariable(name="id") int id, Model model)
    {
        for(Product temp: allProducts) {
            if (temp.getId() == id) {
                allProducts.remove(temp);
                model.addAttribute("product", allProducts);
                return "many-product-page";
            }
        }
        return "error";
    }
}
