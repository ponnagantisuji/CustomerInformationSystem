package repository;

import com.gnyra.dto.Customer;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerDB {
    private final AtomicInteger idGenerator = new AtomicInteger();
    public final Map<Integer, Customer> customerInfoDb = Collections.synchronizedMap(new LinkedHashMap<>());

    public ResponseEntity<Customer> storeCustomerInfo(@Valid Customer customer) {
        int id = idGenerator.incrementAndGet();
        customer.setId(id);
        customerInfoDb.put(id, customer);
        return ResponseEntity.status(201).body(customer);
    }

    public ResponseEntity<Customer> updateCustomerInDb(Integer id, @Valid Customer customer) {
        if (!customerInfoDb.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Customer existingCustomer = customerInfoDb.get(id);
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        customerInfoDb.put(id, existingCustomer);
        return ResponseEntity.ok(existingCustomer);
    }
}