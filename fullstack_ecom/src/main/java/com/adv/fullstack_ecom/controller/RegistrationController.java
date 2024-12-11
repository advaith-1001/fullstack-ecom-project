//package com.adv.fullstack_ecom.controller;
//
//import com.adv.fullstack_ecom.entity.Customer;
//import com.adv.fullstack_ecom.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RegistrationController {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register/user")
//    public String createCustomer(@RequestBody Customer customer) {
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//        customerRepository.save(customer);
//        return "Registration Complete!";
//    }
//}
