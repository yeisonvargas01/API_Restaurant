package com.example.restaurant.controller;

import com.example.restaurant.dto.OrderRequest;
import com.example.restaurant.model.Order;
import com.example.restaurant.usecase.OrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    // POST /api/orders
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = orderUseCase.createOrder(request);
        return ResponseEntity.ok(order);
    }

    // GET /api/orders/active
    @GetMapping("/active")
    public List<Order> getActiveOrders() {
        return orderUseCase.getActiveOrders();
    }

    // GET /api/orders/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderUseCase.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/orders/{id}/close?coupon=0.05
    @PutMapping("/{id}/close")
    public ResponseEntity<Order> closeOrder(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0.0") double coupon
    ) {
        try {
            Order order = orderUseCase.closeOrder(id, coupon);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // DELETE /api/orders/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        try {
            Order canceled = orderUseCase.cancelOrder(id);
            return ResponseEntity.ok(canceled);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

