package com.upc.coreservice.Repository.SubscriptionAndPayment;

import com.upc.coreentities.Security.Account;
import com.upc.coreentities.SubscriptionAndPayment.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByAccountOrderByCreatedDateDesc(Account account);
}
