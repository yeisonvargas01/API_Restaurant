package com.co.api_restaurant.service.jpa;

import com.co.api_restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

}

