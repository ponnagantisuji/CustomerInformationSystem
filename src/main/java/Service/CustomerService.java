package com.gnyra.service;

import com.gnyra.dto.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService {
    private final Map<Integer, Customer> customerMap = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public ResponseEntity<Customer> addCustomer(Customer customer) {
        int id = idCounter.getAndIncrement();
        customer.setId(id);
        customerMap.put(id, customer);
        return ResponseEntity.ok(customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    public ResponseEntity<Customer> getCustomerById(Integer id) {
        Customer customer = customerMap.get(id);
        if (customer == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Customer> updateCustomer(Integer id, Customer customer) {
        if (!customerMap.containsKey(id)) return ResponseEntity.notFound().build();
        customer.setId(id);
        customerMap.put(id, customer);
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<String> deleteCustomer(Integer id) {
        if (customerMap.remove(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Deleted");
    }
}