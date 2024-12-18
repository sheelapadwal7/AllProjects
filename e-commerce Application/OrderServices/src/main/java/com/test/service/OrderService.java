package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.test.model.Orderss;
import com.test.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orderss> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orderss> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Orderss createOrder(Orderss order) {
        return orderRepository.save(order);
    }

    public Orderss updateOrder(Integer id, Orderss orderDetails) {
    	Orderss order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setCustomerId(orderDetails.getCustomerId());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotalAmount(orderDetails.getTotalAmount());
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
    	Orderss order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}

