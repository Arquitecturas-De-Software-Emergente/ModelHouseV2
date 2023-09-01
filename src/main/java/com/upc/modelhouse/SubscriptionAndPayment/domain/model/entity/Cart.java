package com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.modelhouse.security.domain.model.entity.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private Plan plan;

    @JsonIgnore
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Account account;

    private int quantity;
    public Cart(Plan plan, int quantity, Account account){
        this.account = account;
        this.plan = plan;
        this.quantity = quantity;
        this.createdDate = new Date();
    }
    public Cart() {

    }
}
