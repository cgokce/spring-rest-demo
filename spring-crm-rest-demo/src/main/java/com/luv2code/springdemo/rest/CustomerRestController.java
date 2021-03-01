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
    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        // If customerId is not found on the server, it will return null
        Customer theCustomer = customerService.getCustomer(customerId);

        /*
        if (theCustomer == null){
            theCustomer = new Customer();
        }
        */

        return theCustomer;
    }

}
