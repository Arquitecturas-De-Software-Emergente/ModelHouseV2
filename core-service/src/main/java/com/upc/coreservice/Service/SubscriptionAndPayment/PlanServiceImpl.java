package com.upc.coreservice.Service.SubscriptionAndPayment;

import com.upc.coreentities.SubscriptionAndPayment.Plan;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.SubscriptionAndPayment.PlanRepository;
import com.upc.coreservice.Service.Intefaces.PlanService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
