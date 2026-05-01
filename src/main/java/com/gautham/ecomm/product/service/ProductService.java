package com.gautham.ecomm.product.service;

import com.gautham.ecomm.product.dto.CreateProductRequest;
import com.gautham.ecomm.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest request);

    List<Product> getAllProducts();
}
