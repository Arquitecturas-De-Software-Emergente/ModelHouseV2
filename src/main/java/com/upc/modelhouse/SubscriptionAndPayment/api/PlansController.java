package com.upc.modelhouse.SubscriptionAndPayment.api;

import com.upc.modelhouse.SubscriptionAndPayment.domain.service.PlanService;
import com.upc.modelhouse.SubscriptionAndPayment.mapping.PlanMapper;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Plan.CreatePlanDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Plan.PlanDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "Plans")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/plans")
public class PlansController {
    private final PlanService planService;
    private final PlanMapper mapper;

    public PlansController(PlanService planService, PlanMapper mapper) {
        this.planService = planService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<PlanDto> getAll() { return mapper.listToResource(planService.getAll()); }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public PlanDto createPlan(CreatePlanDto dto) { return mapper.toResource(planService.create(mapper.toModel(dto))); }
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deletePlan(Long planId) { return planService.delete(planId); }
}
