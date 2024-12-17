package com.adv.fullstack_ecom.controller;

import com.adv.fullstack_ecom.entity.Customer;
import com.adv.fullstack_ecom.entity.Order;
import com.adv.fullstack_ecom.repository.CustomerRepository;
import com.adv.fullstack_ecom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam String userName, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        return orderService.createOrder(userName, name, address, phoneNumber);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
