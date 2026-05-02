package com.gautham.ecomm.product.service;

import com.gautham.ecomm.product.dto.CreateProductRequest;
import com.gautham.ecomm.product.dto.PagedProductsResponse;
import com.gautham.ecomm.product.dto.ProductResponse;
import com.gautham.ecomm.product.dto.UpdateProductRequest;

import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    PagedProductsResponse getProducts(String nameSearch, Pageable pageable);

    PagedProductsResponse searchProductsByName(String name, Pageable pageable);

    ProductResponse updateProduct(Long productId, UpdateProductRequest request);

    void deleteProduct(Long productId);

    ProductResponse getProductById(Long productId);
}