package com.example.restaurant.adapter;

import com.example.restaurant.model.Product;
import com.example.restaurant.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.example.restaurant.usecase.ProductUseCase;


import java.util.List;
import java.util.Optional;

@Service
public class MySqlProductService implements ProductUseCase {

    private final ProductRepository productRepository;

    public MySqlProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // ---------- MÉTODOS QUE FALTABAN ----------
    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setCategory(updatedProduct.getCategory());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    // ------------------------------------------
}

