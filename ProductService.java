package com.mydata.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydata.Repository.ProductRepository;
import com.mydata.model.Product;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public double calculateTotalCost(Long id, int quantity) {
        Product product = getProductById(id);

        if (product != null) {
            return product.getPrice() * quantity;
        }

        return 0.0;
    }
    public double calculateTotalOfAllTotalPrices() {
        List<Product> products = getAllProducts();
        double totalOfAllTotalPrices = products.stream()
                .mapToDouble(Product::getTotalPrice)
                .sum();

        return totalOfAllTotalPrices;
    }
}