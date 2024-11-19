package com.example.alten.service;

import com.example.alten.entity.Product;
import com.example.alten.exceptions.EntityException;
import com.example.alten.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        product.setCreatedAt(new Date());
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));

    }

    public Product updateProduct(int id, Product product) {
        Product productFound = repository.findById(id)
                .orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));;
        productFound.setName(product.getName());
        productFound.setCode(product.getCode());
        productFound.setDescription(product.getDescription());
        productFound.setImage(product.getImage());
        productFound.setCategory(product.getCategory());
        productFound.setPrice(product.getPrice());
        productFound.setQuantity(product.getQuantity());
        productFound.setInternalReference(product.getInternalReference());
        productFound.setInventoryStatus(product.getInventoryStatus());
        productFound.setShellId(product.getShellId());
        productFound.setRating(product.getRating());
        productFound.setUpdatedAt(new Date());

        return repository.save(productFound);
    }

    public void deleteProduct(int id) {
        Product productFound = repository.findById(id)
                .orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));
        repository.delete(productFound);
    }
}
