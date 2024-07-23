package com.eidiko.product.service;

import com.eidiko.product.dto.ProductDTO;
import com.eidiko.product.entity.Product;
import com.eidiko.product.exception.ProductNotFoundException;
import com.eidiko.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> createProduct(Product product) {
        Product savedProduct = productRepository.save(product); // db 10s

        ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);

        if (savedProductDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some error occurred");
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDTO> productDtoList = allProducts.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        if (!productDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(productDtoList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<?> getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        ProductDTO productDtoByName = modelMapper.map(product, ProductDTO.class);

        if (productDtoByName != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(productDtoByName);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductNotFoundException("Product does not exists"));
    }

    @Override
    public ResponseEntity<?> updateProduct(Integer id, Product product) {
        product.setId(id);
        Product updatedProduct = productRepository.save(product);

        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);

        if (updatedProductDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedProductDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductNotFoundException("Product does not exists"));
    }

    @Override
    public ResponseEntity<?> deleteProduct(int id) {
        Product fetchedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exists"));

        if (fetchedProduct != null) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(fetchedProduct);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed to delete");
    }
}
