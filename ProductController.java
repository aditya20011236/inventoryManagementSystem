package com.mydata.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mydata.model.Product;
import com.mydata.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/totalCost")
    public String showTotalCostPage() {
        return "totalCost";
    }

    @GetMapping("/{id}/totalCost")
    public String calculateTotalCost(@PathVariable Long id, @RequestParam int quantity, Model model) {
        double totalCost = productService.calculateTotalCost(id, quantity);
        model.addAttribute("totalCost", totalCost);
        return "totalCost";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
