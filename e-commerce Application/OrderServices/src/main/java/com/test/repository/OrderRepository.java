package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Orderss;

@Repository
public interface OrderRepository extends JpaRepository<Orderss, Integer> {
}

