package com.adv.fullstack_ecom.service;

import com.adv.fullstack_ecom.entity.*;
import com.adv.fullstack_ecom.repository.CartRepository;
import com.adv.fullstack_ecom.repository.CustomerRepository;
import com.adv.fullstack_ecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public ResponseEntity<?> createOrder(String userName, String name, String address, String phoneNumber) {
        if (userName == null || name == null || address == null || phoneNumber == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        // Retrieve the customer
        Customer customer = customerRepository.findByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        // Retrieve the cart and cart items
        Cart cart = cartRepository.findByCustomerUserName(userName);
        if (cart == null || cart.getCartItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart is empty or not found for this customer");
        }

        // Create a new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setName(name);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);

        // Copy cart items to order items and calculate total price
        double totalPrice = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setOrder(order);

            orderItems.add(orderItem);

            // Calculate total price
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }

        // Set order items and total price
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalPrice);

        // Save the order
        orderRepository.save(order);

        cart.getCartItems().clear(); // Clear the list of cart items
        cartRepository.save(cart);

        return ResponseEntity.ok("Order placed successfully! Total price: " + totalPrice);
    }



    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
