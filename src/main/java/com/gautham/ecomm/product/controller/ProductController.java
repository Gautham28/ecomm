package com.gautham.ecomm.product.controller;

import com.gautham.ecomm.product.dto.CreateProductRequest;
import com.gautham.ecomm.product.dto.ProductResponse;
import com.gautham.ecomm.product.service.ProductService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {

        return productService.createProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }
}