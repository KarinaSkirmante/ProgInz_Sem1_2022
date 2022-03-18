package lv.venta.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyFirstController {

    // URL - localhost:8080/home
    @GetMapping("/home")
    public String getHomePage() {
        System.out.println("Mans kontrolieris ir nostradajis!");
        return "home"; // parādīs home.html lapu
    }
    
}
