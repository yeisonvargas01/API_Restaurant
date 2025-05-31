package com.co.api_restaurant.service.jpa;

import com.co.api_restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}

