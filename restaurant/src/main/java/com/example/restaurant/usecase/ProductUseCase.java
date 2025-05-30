package com.example.restaurant.usecase;

import com.example.restaurant.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    List<Product> getProductsByCategory(String category);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}


