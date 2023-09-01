package com.upc.modelhouse.SubscriptionAndPayment.domain.persistence;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findSubscriptionById(Long id);
    List<Subscription> findAll();
    Subscription findByAccountId(Long id);
    Subscription findByPlanId(Long id);
}
