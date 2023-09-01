package com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity;

import com.upc.modelhouse.SubscriptionAndPayment.resource.Product.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull Double price;
    private @NotNull String description;
    public Product(String name, String imageURL, double price, String description) {
        super();
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
    }
    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.imageURL = productDto.getImageURL();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
    }
    public Product() {
    }
}
