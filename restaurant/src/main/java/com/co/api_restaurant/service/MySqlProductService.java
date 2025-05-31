package com.co.api_restaurant.service;

import com.co.api_restaurant.model.Product;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MySqlProductService implements ProductRepository {
    private final Map<Long, Product> productDB = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDB.values());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productDB.get(id));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productDB.values().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product product) {
        Long id = idGenerator.incrementAndGet();
        product.setId(id);
        productDB.put(id, product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        if (product.getId() != null && productDB.containsKey(product.getId())) {
            productDB.put(product.getId(), product);
            return product;
        }
        throw new IllegalArgumentException("Producto no encontrado");
    }

    @Override
    public void deleteProduct(Long id) {
        productDB.remove(id);
    }
}

