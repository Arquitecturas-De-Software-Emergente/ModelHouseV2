package com.upc.modelhouse.SubscriptionAndPayment.service;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Product;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.ProductRepository;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Product.ProductDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Product.ProductNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<ProductDto> listProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: products){
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    public static ProductDto getDtoFromProduct(Product product) {
        return new ProductDto(product);
    }
    public static Product getProductFromDto(ProductDto productDto) {
        return new Product(productDto);
    }
    public void addProduct(ProductDto productDto) {
        Product product = getProductFromDto(productDto);
        productRepository.save(product);
    }
    public void updateProduct(Integer productID, ProductDto productDto) {
        Product product = getProductFromDto(productDto);
        product.setId(productID);
        productRepository.save(product);
    }
    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }
}
