//package com.adv.fullstack_ecom.service;
//
//import com.adv.fullstack_ecom.entity.Customer;
//import com.adv.fullstack_ecom.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomUserDetailsService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Customer user = customerRepository.findByUserName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(user.getUserName(),
//                user.getPassword(), List.of(new SimpleGrantedAuthority("USER"))); // Set roles here
//    }
//}
