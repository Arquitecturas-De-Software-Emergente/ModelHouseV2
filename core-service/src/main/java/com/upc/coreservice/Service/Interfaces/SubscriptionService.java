package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.SubscriptionAndPayment.Subscription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Subscription findById(Long id);
    Subscription findByAccountId(Long accountId);
    Subscription create(Long accountId, Long planId, Subscription subscription);
    Subscription update(Long id, Subscription subscription);
    ResponseEntity<?> delete(Long id);
}
