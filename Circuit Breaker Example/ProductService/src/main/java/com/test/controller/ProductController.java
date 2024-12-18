package com.test.controller;


import org.springframework.web.bind.annotation.*;

import com.test.model.Product;
import com.test.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/{productId}/inventory")
    public String checkInventory(@PathVariable String productId) {
        return productService.checkInventory(productId);
    }
}

