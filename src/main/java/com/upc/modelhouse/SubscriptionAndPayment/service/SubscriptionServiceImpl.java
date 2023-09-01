package com.upc.modelhouse.SubscriptionAndPayment.service;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Subscription;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.PlanRepository;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.SubscriptionRepository;
import com.upc.modelhouse.SubscriptionAndPayment.domain.service.SubscriptionService;
import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.domain.model.entity.User;
import com.upc.modelhouse.security.domain.persistence.AccountRepository;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.shared.exception.ResourceNotFoundException;
import com.upc.modelhouse.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final AccountRepository accountRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
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
