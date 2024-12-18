package com.test.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.test.model.Product;
import com.test.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final WebClient.Builder webClientBuilder;

    public ProductService(ProductRepository productRepository, WebClient.Builder webClientBuilder) {
        this.productRepository = productRepository;
        this.webClientBuilder = webClientBuilder;
    }

    // Save a product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Fetch product details
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Call Inventory Service to check stock availability
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
    public String checkInventory(String productId) {
        return webClientBuilder.baseUrl("http://localhost:8081")  // Inventory Service running on port 8081
                .build()
                .get()
                .uri("/api/inventory/" + productId)  
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Fallback method if Inventory Service fails
    public String inventoryFallback(String productId, Throwable ex) {
        return "Could not retrieve stock information for Product ID: " + productId + ". Reason: " + ex.getMessage();
    }
}
