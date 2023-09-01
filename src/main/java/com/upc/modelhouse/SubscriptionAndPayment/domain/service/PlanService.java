package com.upc.modelhouse.SubscriptionAndPayment.domain.service;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAll();
    Plan findById(Long id);
    Plan create(Plan plan);
    ResponseEntity<?> delete(Long id);
}
