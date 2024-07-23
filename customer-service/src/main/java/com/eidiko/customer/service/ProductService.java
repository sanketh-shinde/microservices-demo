package com.eidiko.customer.service;

import com.eidiko.customer.dto.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> createProduct(Product product);

    ResponseEntity<?> getProductByName(String name);

    ResponseEntity<List<?>> getAllProducts();

    ResponseEntity<?> updateProduct(Integer id, Product product);

    ResponseEntity<?> deleteProduct(Integer id);

}
