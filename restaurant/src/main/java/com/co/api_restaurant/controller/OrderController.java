package com.co.api_restaurant.controller;

import com.co.api_restaurant.model.Order;
import com.co.api_restaurant.usecase.OrderUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderUseCase.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderUseCase.getOrderById(id);
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderUseCase.saveOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        order.setId(id);
        return orderUseCase.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderUseCase.deleteOrder(id);
    }

    // Endpoints específicos para pedidos

    @GetMapping("/active")
    public List<Order> getActiveOrders() {
        return orderUseCase.getActiveOrders();
    }

    // Cerrar pedido y aplicar descuento (por ejemplo: /api/orders/1/close?discount=10)
    @PostMapping("/{id}/close")
    public Order closeOrder(@PathVariable Long id, @RequestParam(required = false, defaultValue = "0") double discount) {
        return orderUseCase.closeOrder(id, discount);
    }

    // Cancelar pedido (por ejemplo: /api/orders/1/cancel)
    @PostMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id) {
        return orderUseCase.cancelOrder(id);
    }
}
