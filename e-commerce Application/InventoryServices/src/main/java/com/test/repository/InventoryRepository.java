package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}

