package com.adv.fullstack_ecom.controller;

import com.adv.fullstack_ecom.entity.Cart;
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
    public ResponseEntity<String> removeItemFromCart(@RequestParam String userName, @RequestParam Long productId){
        cartService.removeItemFromCart(userName, productId);
        return ResponseEntity.ok("Item removed");
    }

    @PutMapping("/update-item")
    public ResponseEntity<String> updateItemFromCart(Long productId, int quantity) {
        cartService.updateItemQuantity(productId, quantity);
        return ResponseEntity.ok("Item quantity Updated");
    }

    @GetMapping("/get-items")
    public ResponseEntity<List<CartItem>> viewCart(@RequestParam String userName) {
        return ResponseEntity.ok(cartService.getCartItemsByUserName(userName));
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalCartPrice(@RequestParam String userName) {
        return ResponseEntity.ok(cartService.getTotalCartPrice(userName));
    }

}
