//package com.adv.fullstack_ecom.service;
//
//import com.adv.fullstack_ecom.entity.Customer;
//import com.adv.fullstack_ecom.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CustomerService {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    @Lazy
//    private PasswordEncoder passwordEncoder;
//
//    public void registerUser(String username, String password) {
//        // Ensure the user doesn't already exist
//        if (customerRepository.findByUserName(username).isPresent()) {
//            throw new RuntimeException("User already exists!");
//        }
//
//        // Hash the password for security
//        String hashedPassword = passwordEncoder.encode(password);
//
//        // Create a new Customer entity and save it to the database
//        Customer newCustomer = new Customer();
//        newCustomer.setUserName(username);
//        newCustomer.setPassword(hashedPassword);
//
//        customerRepository.save(newCustomer);
//
//    }
//
//    public Customer authenticateUser(String username, String password) {
//        Customer customer = customerRepository.findByUserName(username)
//                .orElseThrow(() -> new RuntimeException("Invalid username or password."));
//
//        if (passwordEncoder.matches(password, customer.getPassword())) {
//            return customer; // authentication successful
//        } else {
//            throw new RuntimeException("Invalid credentials!");
//        }
//
//    }
//
//}



