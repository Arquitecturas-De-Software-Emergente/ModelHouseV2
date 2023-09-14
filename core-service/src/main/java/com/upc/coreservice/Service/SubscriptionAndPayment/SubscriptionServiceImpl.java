package com.upc.coreservice.Service.SubscriptionAndPayment;

import com.upc.coreentities.Security.Account;
import com.upc.coreentities.SubscriptionAndPayment.Plan;
import com.upc.coreentities.SubscriptionAndPayment.Subscription;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Repository.SubscriptionAndPayment.PlanRepository;
import com.upc.coreservice.Repository.SubscriptionAndPayment.SubscriptionRepository;
import com.upc.coreservice.Service.Intefaces.SubscriptionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final AccountRepository accountRepository;
    private final PlanRepository planRepository;
    private Validator validator;

    private static final String ENTITY = "Subscription";
    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription findById(Long id) {
        return subscriptionRepository.findSubscriptionById(id);
    }

    @Override
    public Subscription findByAccountId(Long userId) {
        return subscriptionRepository.findByAccountId(userId);
    }

    @Override
    public Subscription create(Long accountId, Long planId, Subscription subscription) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(subscription);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        subscription.setActivatedAt(new Date());
        return accountRepository.findById(accountId).map(account -> {
            subscription.setAccount(account);
            subscription.getAccount().getUser().setRole("business");
            subscription.setActivated(true);
            Plan plan = planRepository.findPlanById(planId);
            if(plan == null)
                throw new ResourceNotFoundException("This plan doesn't exist");
            subscription.setPlan(plan);
            Account accountExist = accountRepository.findAccountById(accountId);
            if(accountExist == null)
                throw new ResourceNotFoundException("This account doesn't exist");
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new ResourceNotFoundException("Account", accountId));

    }

    @Override
    public Subscription update(Long id, Subscription subscription) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return subscriptionRepository.findById(id).map(subscription -> {
            subscriptionRepository.delete(subscription);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
