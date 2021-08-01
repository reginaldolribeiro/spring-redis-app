package com.example.springredisapp.controller;

import com.example.springredisapp.model.Customer;
import com.example.springredisapp.service.CustomerService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Cacheable(value = "customers")
    public Iterable<Customer> home() {
        System.out.println("Customer fetching from database...");
        return customerService.findAll();
    }

//    @GetMapping("{id}")
//    @Cacheable(value = "customers", key = "#id", unless = "#id == 2")
//    public Customer findById(@PathVariable Long id) {
//        System.out.println("Customer " + id + " fetching from database...");
//        return customerService.findById(id);
//    }

    @GetMapping("{id}")
    @Cacheable(value = "customers", key = "#id")
    public Customer findById(@PathVariable Long id) throws InterruptedException {
        System.out.println("Customer " + id + " fetching from database...");
//        Thread.sleep(2000);
        return customerService.findById(id);
    }

    @GetMapping("/clear")
    @CacheEvict(value = "customers") // Cleans just customers cache
//    @CacheEvict(value = "customers", allEntries = true) // Clean entire cache
    public String clearCache() {
        System.out.println("Cleaning cache...");
        return "Cleaning cache...";
    }

//    @GetMapping("/update")
//    @CachePut(value = "customers") // Update customers cache with result of this method
//    public String updateCache() {
//        System.out.println("Updating cache...");
//        return "Updating cache...";
//    }

    @PostMapping
    @CachePut(value = "customers", key = "#customer.id")
    public Customer save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return savedCustomer;
    }
}
