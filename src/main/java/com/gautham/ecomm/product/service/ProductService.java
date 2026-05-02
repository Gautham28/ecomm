package com.gautham.ecomm.product.service;

import com.gautham.ecomm.product.dto.CreateProductRequest;
import com.gautham.ecomm.product.dto.ProductResponse;
import com.gautham.ecomm.product.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(
            CreateProductRequest request
    );

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(
            Long productId,
            UpdateProductRequest request
    );

    void deleteProduct(Long productId);
}