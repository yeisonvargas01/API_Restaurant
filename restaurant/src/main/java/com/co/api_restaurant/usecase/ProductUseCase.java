package com.co.api_restaurant.usecase;

import com.co.api_restaurant.model.Product;
import com.co.api_restaurant.service.ProductRepository;
import java.util.List;
import java.util.Optional;

public class ProductUseCase {
    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}

