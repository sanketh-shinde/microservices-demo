package com.eidiko.product.service;

import com.eidiko.product.dto.ProductDTO;
import com.eidiko.product.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> createProduct(Product product);

    ResponseEntity<List<ProductDTO>> getAllProducts();

    ResponseEntity<?> getProductByName(String name);

    ResponseEntity<?> updateProduct(Integer id, Product product);

    ResponseEntity<?> deleteProduct(int id);
}
