package com.example.alten.controller;

import com.example.alten.entity.Product;
import com.example.alten.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PatchMapping("/api/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();

    }
}
