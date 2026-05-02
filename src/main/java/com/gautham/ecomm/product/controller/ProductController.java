package com.gautham.ecomm.product.controller;

import com.gautham.ecomm.product.dto.CreateProductRequest;
import com.gautham.ecomm.product.dto.PagedProductsResponse;
import com.gautham.ecomm.product.dto.ProductResponse;
import com.gautham.ecomm.product.dto.UpdateProductRequest;
import com.gautham.ecomm.product.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {

        return productService.createProduct(request);
    }

    @GetMapping
    public PagedProductsResponse getProducts(
            @RequestParam(required = false) String name,
            @PageableDefault(
                    size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ) {

        return productService.getProducts(name, pageable);
    }

    @GetMapping("/search")
    public PagedProductsResponse searchProducts(
            @RequestParam @NotBlank(message = "name is required") String name,
            @PageableDefault(
                    size = 20,
                    sort = "name",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ) {

        return productService.searchProductsByName(name, pageable);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {

        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }
}