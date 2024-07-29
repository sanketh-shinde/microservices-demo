package com.eidiko.customer.service;

import com.eidiko.customer.dto.CustomerDTO;
import com.eidiko.customer.entity.Customer;
import com.eidiko.customer.exception.NotFoundException;
import com.eidiko.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO savedCustomerDTO = modelMapper.map(savedCustomer, CustomerDTO.class);

        if (savedCustomerDTO != null) {
            log.info("Customer created with id: {}",savedCustomer.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
        }

        log.error("Some error occurred");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error occurred");
    }

    @Override
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();

        List<CustomerDTO> list = customerList.stream()
                                             .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                                             .toList();

        if (!list.isEmpty()) {
            log.info("Customers Fetched");
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }

        log.error("No Customer Available");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error occurred");
    }

    @Override
    public ResponseEntity<?> getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email does not exists"));

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        if (customerDTO != null) {
            log.info("Customer Found with email: {}", email);
            return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
        }

        log.error("Customer with email: {}, does not exists",email);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException("Email does not exists"));
    }

    @Override
    public ResponseEntity<?> updateCustomer(Customer customer) {
        Customer updatedCustomer = customerRepository.save(customer);

        CustomerDTO updatedCustomerDTO = modelMapper.map(updatedCustomer, CustomerDTO.class);

        if (updatedCustomerDTO != null) {
            log.info("Customer updated");
            return ResponseEntity.status(HttpStatus.OK).body(updatedCustomerDTO);
        }

        log.info("Customer does not exists");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException("Customer does not exists"));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Integer id) {
        Customer deletedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exists"));

        if (deletedCustomer != null) {
            customerRepository.deleteById(id);
            log.info("Customer with id: {}, deleted successfully", id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedCustomer);
        }

        log.error("Customer with id: {}, does not exists", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException("Customer does not exists"));
    }

}
