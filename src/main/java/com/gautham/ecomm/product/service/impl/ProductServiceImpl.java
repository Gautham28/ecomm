package com.gautham.ecomm.product.service.impl;

import com.gautham.ecomm.product.dto.CreateProductRequest;
import com.gautham.ecomm.product.dto.ProductResponse;
import com.gautham.ecomm.product.entity.Product;
import com.gautham.ecomm.product.repository.ProductRepository;
import com.gautham.ecomm.product.service.ProductService;
import com.gautham.ecomm.user.entity.User;
import com.gautham.ecomm.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gautham.ecomm.product.dto.UpdateProductRequest;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Override
    public ProductResponse createProduct(
            CreateProductRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User seller = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .seller(seller)
                .createdAt(LocalDateTime.now())
                .build();

        Product savedProduct =
                productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(
            Product product
    ) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(BigDecimal.valueOf(product.getPrice()))
                .stock(product.getStock())
                .sellerEmail(product.getSeller().getEmail())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Override
    public ProductResponse updateProduct(
            Long productId,
            UpdateProductRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (!product.getSeller()
                .getEmail()
                .equals(email)) {

            throw new RuntimeException(
                    "You are not allowed to update this product"
            );
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice().doubleValue());
        product.setStock(request.getStock());

        Product updatedProduct =
                productRepository.save(product);

        return mapToResponse(updatedProduct);
    }
}