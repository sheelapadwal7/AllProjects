package com.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.model.Orderss;
import com.test.service.OrderService;

@RestController
@RequestMapping("/orders")
class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orderss")
	public String getOrders() {
		return "Order List: Order 1, Order 2, Order 3";
	}

	@GetMapping
	public List<Orderss> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
		Optional<Orderss> order = orderService.getOrderById(id);
		if (order.isPresent()) {
			return ResponseEntity.ok().body(order.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with id: " + id);
		}
	}

	@PostMapping
	public Orderss createOrder(@RequestBody Orderss order) {
		return orderService.createOrder(order);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Orderss> updateOrder(@PathVariable Integer id, @RequestBody Orderss orderDetails) {
		Orderss updatedOrder = orderService.updateOrder(id, orderDetails);
		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
}
