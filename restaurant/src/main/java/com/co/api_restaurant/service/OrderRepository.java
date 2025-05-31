package com.co.api_restaurant.service;

import com.co.api_restaurant.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order saveOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
}
