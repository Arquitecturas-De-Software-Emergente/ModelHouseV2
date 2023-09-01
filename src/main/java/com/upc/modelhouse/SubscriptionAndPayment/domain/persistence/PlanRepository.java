package com.upc.modelhouse.SubscriptionAndPayment.domain.persistence;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findPlanById(Long id);
    List<Plan> findAll();
}
