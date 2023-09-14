package com.upc.coreentities.SubscriptionAndPayment;

import com.upc.coreentities.Security.Account;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date activatedAt;
    private Date updatedAt;
    private Boolean activated;
    private Boolean autoRenewal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;
}
