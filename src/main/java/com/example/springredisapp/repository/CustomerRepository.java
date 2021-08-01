package com.example.springredisapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springredisapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
