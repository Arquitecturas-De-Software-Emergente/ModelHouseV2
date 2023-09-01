package com.upc.modelhouse.SubscriptionAndPayment.resource.Product;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull Double price;
    private @NotNull String description;
    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageURL(product.getImageURL());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
    }
    public ProductDto(@NotNull String name, @NotNull String imageURL, @NotNull double price, @NotNull String description) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
    }
}
