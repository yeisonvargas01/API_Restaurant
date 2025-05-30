package com.example.restaurant.usecase;

import com.example.restaurant.dto.OrderRequest;
import com.example.restaurant.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderUseCase {

    Order createOrder(OrderRequest request);

    List<Order> getActiveOrders();

    Optional<Order> getOrderById(Long id);

    Order closeOrder(Long id, double coupon);

    Order cancelOrder(Long id);
}

