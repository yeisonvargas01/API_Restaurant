package com.co.api_restaurant.service;

import com.co.api_restaurant.model.Order;
import com.co.api_restaurant.service.jpa.JpaOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MySqlOrderService implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public MySqlOrderService(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return jpaOrderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return jpaOrderRepository.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return jpaOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        jpaOrderRepository.deleteById(id);
    }
}


