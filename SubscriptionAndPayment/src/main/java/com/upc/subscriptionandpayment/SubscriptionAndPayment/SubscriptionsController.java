package com.upc.subscriptionandpayment.SubscriptionAndPayment;

import com.upc.coreentities.Resource.Subscription.CreateSubscriptionDto;
import com.upc.coreentities.Resource.Subscription.SubscriptionDto;
import com.upc.coreservice.Mapping.SubscriptionMapper;
import com.upc.coreservice.Service.Intefaces.SubscriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "Subscriptions")
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class SubscriptionsController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper mapper;

    public SubscriptionsController(SubscriptionService subscriptionService, SubscriptionMapper mapper) {
        this.subscriptionService = subscriptionService;
        this.mapper = mapper;
    }

    @GetMapping("/subscriptions")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<SubscriptionDto> getAll() { return mapper.listToResource(subscriptionService.getAll()); }

    @PostMapping("/account/{accountId}/plans/{planId}/subscriptions")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public SubscriptionDto createSubscription(Long accountId, Long planId, @RequestBody CreateSubscriptionDto createSubscriptionDto) { return mapper.toResource(subscriptionService.create(accountId, planId, mapper.toModel(createSubscriptionDto))); }

    @DeleteMapping("/subscriptions/{subscriptionId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteSubscription(Long subscriptionId) { return subscriptionService.delete(subscriptionId); }
}
