package com.eidiko.customer.feignclient;

import com.eidiko.customer.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE", path = "/product")
public interface ProductClient {

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Product product);

    @GetMapping(value = "/getAllProducts")
    ResponseEntity<List<?>> getAll();

    @GetMapping(value = "/getProduct/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> get(@PathVariable String name);

    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Product product);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> delete(@PathVariable int id);


}
