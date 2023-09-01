package com.upc.modelhouse.SubscriptionAndPayment.api;

import com.upc.modelhouse.SubscriptionAndPayment.Common.ApiResponse;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Product.ProductDto;
import com.upc.modelhouse.SubscriptionAndPayment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) {
        productService.updateProduct(productID, productDto);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
