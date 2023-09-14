package com.upc.coreservice.Repository.SubscriptionAndPayment;

import com.upc.coreentities.SubscriptionAndPayment.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
