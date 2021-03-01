package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // Injects the customer service
    @Autowired
    private CustomerService customerService;

    // Add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    // Return a customer for the given id
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        // If customerId is not found on the server, it will return null
        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null){
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return theCustomer;
    }

    // add mapping for POST /customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer){
        // Our backend DAO code uses Hibernate method saveOrUpdate()
        // If id primaryKey is empty(null or 0) then INSERT, so we overwrite the id to 0
        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    // add mapping for PUT /customers - update existing customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer){

        // Update the customer based on the primaryKey id
        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

}
