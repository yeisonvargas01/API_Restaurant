package com.co.api_restaurant.service;

import com.co.api_restaurant.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    List<Product> getProductsByCategory(String category);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
