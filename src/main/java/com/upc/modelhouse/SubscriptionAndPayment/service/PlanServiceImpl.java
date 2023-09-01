package com.upc.modelhouse.SubscriptionAndPayment.service;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.PlanRepository;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.SubscriptionRepository;
import com.upc.modelhouse.SubscriptionAndPayment.domain.service.PlanService;
import com.upc.modelhouse.shared.exception.ResourceNotFoundException;
import com.upc.modelhouse.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final Validator validator;
    private static final String ENTITY = "Plan";

    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan findById(Long id) {
        return planRepository.findPlanById(id);
    }

    @Override
    public Plan create(Plan plan) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);
        if(!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Plan planExist = planRepository.findPlanById(plan.getId());
        if(planExist != null) {
            throw new ResourceNotFoundException("This plan already exist");
        }
        return planRepository.save(plan);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return planRepository.findById(id).map(plan -> {
            planRepository.delete(plan);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

}
