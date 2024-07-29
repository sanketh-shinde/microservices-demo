package com.eidiko.product.service;

import com.eidiko.product.dto.ProductDTO;
import com.eidiko.product.entity.Product;
import com.eidiko.product.exception.ProductNotFoundException;
import com.eidiko.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
            log.info("Product created with id: {}", savedProduct.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
        }

        log.error("Some error occurred");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some error occurred");
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDTO> productDtoList = allProducts.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        if (!productDtoList.isEmpty()) {
            log.info("Product fetched");
            return ResponseEntity.status(HttpStatus.FOUND).body(productDtoList);
        }

        log.error("No Products available");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<?> getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        ProductDTO productDtoByName = modelMapper.map(product, ProductDTO.class);

        if (productDtoByName != null) {
            log.info("Product fetched by name: {}", productDtoByName);
            return ResponseEntity.status(HttpStatus.FOUND).body(productDtoByName);
        }

        log.error("product with the name: {}, does not exists", name);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductNotFoundException("Product does not exists"));
    }

    @Override
    public ResponseEntity<?> updateProduct(Integer id, Product product) {
        product.setId(id);
        Product updatedProduct = productRepository.save(product);

        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);

        if (updatedProductDTO != null) {
            log.info("Product updated successfully: {}", updatedProductDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProductDTO);
        }

        log.error("Product with the id: {}, does not exists", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductNotFoundException("Product does not exists"));
    }

    @Override
    public ResponseEntity<?> deleteProduct(int id) {
        Product fetchedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exists"));

        if (fetchedProduct != null) {
            productRepository.deleteById(id);
            log.info("Product with the id: {}, deleted successfully", id);
            return ResponseEntity.status(HttpStatus.OK).body(fetchedProduct);
        }

        log.error("Failed, Product with the id: {}, does not exists", id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed to delete");
    }
}
