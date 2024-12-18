package com.test.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class InventoryController {

    private static final Map<String, Integer> inventoryData = new HashMap<>();
    static {
        inventoryData.put("p123", 100);
        inventoryData.put("product2", 200);
        inventoryData.put("product3", 300);
    }

    private static final Random random = new Random();

    @GetMapping("/inventory/{productId}")
    public String getInventory(@PathVariable String productId) {
        // Simulate a failure with a random chance
        if (random.nextInt(10) < 3) {  // 30% failure rate
            throw new RuntimeException("Simulated failure");
        }

        Integer quantity = inventoryData.get(productId);
        if (quantity != null) {
            return "Inventory for " + productId + ": " + quantity + " items.";
        } else {
            return "Product not found in inventory.";
        }
    }
}

