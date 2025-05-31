package com.co.api_restaurant.usecase;

import com.co.api_restaurant.service.*;
import com.co.api_restaurant.service.jpa.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantApplicationConfig {

    @Bean
    public ProductRepository productRepository(JpaProductRepository jpaProductRepository) {
        return new MySqlProductService(jpaProductRepository);
    }

    @Bean
    public OrderRepository orderRepository(JpaOrderRepository jpaOrderRepository) {
        return new MySqlOrderService(jpaOrderRepository);
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCase(productRepository);
    }

    @Bean
    public OrderUseCase orderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
        return new OrderUseCase(orderRepository, productRepository);
    }
}
