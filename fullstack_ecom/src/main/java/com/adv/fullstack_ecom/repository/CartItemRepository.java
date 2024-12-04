package com.adv.fullstack_ecom.repository;

import com.adv.fullstack_ecom.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProductId(Long productId);
    List<CartItem> findAllByUserName(String username);

}
