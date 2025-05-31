package com.co.api_restaurant;

import com.co.api_restaurant.service.*;
import com.co.api_restaurant.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantApplicationConfig {

    @Bean
    public ProductRepository productRepository() {
        return new MySqlProductService();
    }

    @Bean
    public OrderRepository orderRepository() {
        return new MySqlOrderService();
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
