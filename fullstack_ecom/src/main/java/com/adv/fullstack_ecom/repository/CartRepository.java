package com.adv.fullstack_ecom.repository;

import com.adv.fullstack_ecom.entity.Cart;
import com.adv.fullstack_ecom.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long customerId);
    Cart findByCustomerUserName(String username);
    void deleteByCustomerUserName(String username);
}
