package com.gautham.ecomm.product.repository;

import com.gautham.ecomm.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
}