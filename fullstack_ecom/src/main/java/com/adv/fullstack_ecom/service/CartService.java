package com.adv.fullstack_ecom.service;

import com.adv.fullstack_ecom.entity.Cart;
import com.adv.fullstack_ecom.entity.CartItem;
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
        if(cartRepository.findByCustomerUserName(username) == null) {
            return new Cart();
        }

        return cartRepository.findByCustomerUserName(username);
    }

    public void addItemToCart(String username, Long productId, int quantity) {

        Cart cart = getCartByCustomerUserName(username);

        Optional<Product> product = productRepository.findById(productId);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product.get());
        cartItem.setQuantity(quantity);
        cartItem.setPrice(product.get().getPrice() * quantity);

        cart.getCartItems().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());

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
