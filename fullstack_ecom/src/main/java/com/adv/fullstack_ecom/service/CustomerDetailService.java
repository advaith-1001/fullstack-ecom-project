//package com.adv.fullstack_ecom.service;
//
//import com.adv.fullstack_ecom.entity.Customer;
//import com.adv.fullstack_ecom.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomerDetailService implements UserDetailsService {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Customer> customer = customerRepository.findByUserName(username);
//
//        if (customer.isPresent()) {
//            return User.builder()
//                    .username(customer.get().getUserName())
//                    .password(customer.get().getPassword())
//                    .roles(customer.get().getRole())
//                    .build();
//        } else {
//            throw new UsernameNotFoundException(username);
//        }
//    }
//
//    private String[] getRoles(Customer customer) {
//        if (customer.getRole() == null) {
//            return new String[]{"USER"};
//        }
//        return customer.getRole().split(",");
//    }
//}
