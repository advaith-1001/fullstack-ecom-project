package com.adv.fullstack_ecom.repository;

import com.adv.fullstack_ecom.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserName(String username);

}
