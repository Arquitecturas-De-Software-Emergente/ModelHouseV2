package com.upc.modelhouse.SubscriptionAndPayment.domain.persistence;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
