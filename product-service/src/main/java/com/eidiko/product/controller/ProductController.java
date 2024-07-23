package com.eidiko.product.controller;

import com.eidiko.product.dto.ProductDTO;
import com.eidiko.product.entity.Product;
import com.eidiko.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProduct/{name}")
    public ResponseEntity<?> get(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
