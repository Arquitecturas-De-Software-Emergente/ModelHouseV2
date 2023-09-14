package com.upc.coreservice.Service.Intefaces;

import com.upc.coreentities.SubscriptionAndPayment.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAll();
    Plan findById(Long id);
    Plan create(Plan plan);
    ResponseEntity<?> delete(Long id);
}
