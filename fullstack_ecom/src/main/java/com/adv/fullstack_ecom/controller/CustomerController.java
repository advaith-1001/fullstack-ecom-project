//package com.adv.fullstack_ecom.controller;
//
//import com.adv.fullstack_ecom.entity.Customer;
//import com.adv.fullstack_ecom.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:5173")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    @Lazy
//    public CustomerController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {
//        String userName = request.get("userName");
//        String password = request.get("password");
//        try {
//            customerService.registerUser(userName, password);
//            return ResponseEntity.ok("User registered successfully!");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(username, password);
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return ResponseEntity.ok("Login successful");
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//
//
//
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout() {
//        return ResponseEntity.ok("Logout successful");
//    }
//}

