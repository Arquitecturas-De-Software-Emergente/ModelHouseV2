package com.upc.modelhouse.SubscriptionAndPayment.resource.Cart;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemDto {
    private @NotNull Integer quantity;
    private @NotNull Plan plan;
    public CartItemDto() {
    }
    public CartItemDto(Plan plan, int quantity) {
        this.plan = plan;
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "CartDto{" +
                ", quantity=" + quantity +
                ", productName=" + plan.getName() +
                '}';
    }
}
