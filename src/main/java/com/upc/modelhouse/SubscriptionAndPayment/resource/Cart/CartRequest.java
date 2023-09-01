package com.upc.modelhouse.SubscriptionAndPayment.resource.Cart;

public class CartRequest {
    private Long planId;
    private Long accountId;

    public Long getAccountId(){
        return accountId;
    }
    public void setAccountId(Long accountId){
        this.accountId = accountId;
    }

    public Long getPlanId(){
        return planId;
    }
    public void setPlanId(Long planId){
        this.planId = planId;
    }
}
