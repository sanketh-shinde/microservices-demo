package com.eidiko.customer.service;

import com.eidiko.customer.dto.Product;
import com.eidiko.customer.feignclient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public ResponseEntity<?> createProduct(Product product) {
//        return restTemplate.postForObject("http://localhost:8081/product/create", product, Product.class);

//        return webClient.post()
//                .uri("/create")
//                .bodyValue(product)
//                .retrieve()
//                .bodyToMono(Product.class)
//                .block();

        return productClient.create(product);
    }

    @Override
    public ResponseEntity<?> getProductByName(String name) {
//        return webClient.get()
//                        .uri("/getProduct/{name}/{age}", name)
//                        .retrieve()
//                        .bodyToMono(Product.class)
//                        .block();

        return productClient.get(name);
    }

    @Override
    public ResponseEntity<List<?>> getAllProducts() {
//        return webClient.method(HttpMethod.GET)
//                .uri("/getAllProducts")
//                .retrieve()
//                .bodyToMono(List.class)
//                .block();

        return productClient.getAll();
    }

    @Override
    public ResponseEntity<?> updateProduct(Integer id, Product product) {
//        return webClient.put()
//                .uri("/update/{id}", id)
//                .bodyValue(product)
//                .retrieve()
//                .bodyToMono(Product.class)
//                .block();

        return productClient.update(id, product);
    }

    @Override
    public ResponseEntity<?> deleteProduct(Integer id) {
//        return webClient.delete()
//                .uri("/delete/{id}", id)
//                .retrieve()
//                .bodyToMono(Product.class)
//                .block();

        return productClient.delete(id);
    }
}
