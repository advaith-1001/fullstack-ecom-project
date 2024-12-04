package com.adv.fullstack_ecom.controller;

import com.adv.fullstack_ecom.entity.CartItem;
import com.adv.fullstack_ecom.entity.Customer;
import com.adv.fullstack_ecom.repository.ProductRepository;
import com.adv.fullstack_ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add-item")
    public ResponseEntity<String> addItemToCart(@RequestParam String userName, @RequestParam Long productId, @RequestParam int quantity) {// Get the logged-in user's username
        cartService.addItemToCart(userName, productId, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @DeleteMapping("/remove-item")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Long productId){
        cartService.removeItemFromCart(productId);
        return ResponseEntity.ok("Item removed");
    }

    @PutMapping("/update-item")
    public ResponseEntity<String> updateItemFromCart(Long productId, int quantity) {
        cartService.updateItemQuantity(productId, quantity);
        return ResponseEntity.ok("Item quantity Updated");
    }

//    @GetMapping
//    public ResponseEntity<List<CartItem>> viewCart(Authentication auth) {
//           String username = auth.getName();
//        return ResponseEntity.ok(cartService.getCartItemsByUserName(username));
//    }

}
