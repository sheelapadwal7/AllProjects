package com.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

