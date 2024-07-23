package com.eidiko.customer.controller;

import com.eidiko.customer.dto.Product;
import com.eidiko.customer.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<List<?>> getAll() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}
