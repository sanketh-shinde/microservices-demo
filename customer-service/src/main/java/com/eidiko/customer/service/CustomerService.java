package com.eidiko.customer.service;

import com.eidiko.customer.dto.Product;
import com.eidiko.customer.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    ResponseEntity<?> createCustomer(Customer customer);

    ResponseEntity<?> getAllCustomers();

    ResponseEntity<?> getCustomerByEmail(String email);

    ResponseEntity<?> updateCustomer(Customer customer);

    ResponseEntity<?> deleteCustomer(Integer id);

}
