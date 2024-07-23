package com.eidiko.customer.controller;

import com.eidiko.customer.entity.Customer;
import com.eidiko.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomer/{email}")
    public ResponseEntity<?> get(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

}