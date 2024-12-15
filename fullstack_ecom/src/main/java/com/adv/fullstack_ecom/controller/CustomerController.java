package com.adv.fullstack_ecom.controller;

import com.adv.fullstack_ecom.entity.Customer;
import com.adv.fullstack_ecom.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Customer customer) {
        // Check if username already exists
        if (customerRepository.findByUserName(customer.getUserName()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Username already exists"));
        }

        // Save the user (hash the password in a real application)
        customer.setPassword(customer.getPassword()); // Replace with BCrypt hashing
        customerRepository.save(customer);

        return ResponseEntity.ok(Map.of("message", "Signup successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer customer, HttpSession session) {
        Optional<Customer> existingUser = customerRepository.findByUserName(customer.getUserName());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(customer.getPassword())) {
            // Store user in session
            session.setAttribute("user", existingUser.get());
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> currentUser(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("user");
        if (customer != null) {
            return ResponseEntity.ok(customer.getUserName());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user logged in");
    }
}


