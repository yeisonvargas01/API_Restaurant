package com.co.api_restaurant.service;

import com.co.api_restaurant.model.Order;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MySqlOrderService implements OrderRepository {
    private final Map<Long, Order> orderDB = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderDB.values());
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return Optional.ofNullable(orderDB.get(id));
    }

    @Override
    public Order saveOrder(Order order) {
        Long id = idGenerator.incrementAndGet();
        order.setId(id);
        orderDB.put(id, order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        if (order.getId() != null && orderDB.containsKey(order.getId())) {
            orderDB.put(order.getId(), order);
            return order;
        }
        throw new IllegalArgumentException("Pedido no encontrado");
    }

    @Override
    public void deleteOrder(Long id) {
        orderDB.remove(id);
    }
}

