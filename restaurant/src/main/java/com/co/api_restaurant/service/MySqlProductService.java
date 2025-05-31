package com.co.api_restaurant.service;

import com.co.api_restaurant.model.Product;
import com.co.api_restaurant.service.jpa.JpaProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MySqlProductService implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public MySqlProductService(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return jpaProductRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return jpaProductRepository.findByCategory(category);
    }

    @Override
    public Product saveProduct(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        jpaProductRepository.deleteById(id);
    }
}
