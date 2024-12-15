package com.adv.fullstack_ecom.service;

import com.adv.fullstack_ecom.entity.Cart;
import com.adv.fullstack_ecom.entity.CartItem;
import com.adv.fullstack_ecom.entity.Customer;
import com.adv.fullstack_ecom.entity.Product;
import com.adv.fullstack_ecom.repository.CartItemRepository;
import com.adv.fullstack_ecom.repository.CartRepository;
import com.adv.fullstack_ecom.repository.CustomerRepository;
import com.adv.fullstack_ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Cart getCartByCustomerUserName(String username) {
        Customer customer = customerRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with username: " + username));

        // Return the existing cart if it exists
        if (customer.getCart() != null) {
            return customer.getCart();
        }

        // Create a new cart if it doesn't exist
        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        customer.setCart(newCart); // Set the relationship
        cartRepository.save(newCart); // Save the new cart to the database

        return newCart;
    }


    public void addItemToCart(String username, Long productId, int quantity) {

        Cart cart = getCartByCustomerUserName(username);

        // Find the product by ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        // Check if the product already exists in the cart
        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // If the product is already in the cart, update its quantity and price
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setPrice(cartItem.getPrice() + (product.getPrice() * quantity));
        } else {
            // If the product is not in the cart, add it as a new item
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setPrice(product.getPrice() * quantity);
            newCartItem.setCart(cart);
            cart.getCartItems().add(newCartItem);
        }

        // Update the total price of the cart
        double updatedTotalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        cart.setTotalPrice(updatedTotalPrice);

        // Save the cart
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Long productId) {
        cartItemRepository.delete(cartItemRepository.findByProductId(productId));
    }

    public void updateItemQuantity(Long productId, int quantity) {
        CartItem cartItem = cartItemRepository.findByProductId(productId);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItemsByCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        return cart.getCartItems();
    }


    public List<CartItem> getCartItemsByUserName(String username) {
        return cartItemRepository.findAllByUserName(username);
    }
}
