package com.upc.modelhouse.SubscriptionAndPayment.domain.persistence;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Cart;
import com.upc.modelhouse.security.domain.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByAccountOrderByCreatedDateDesc(Account account);
}
