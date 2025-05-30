package com.example.restaurant.adapter;

import com.example.restaurant.dto.OrderRequest;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.Product;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.ProductRepository;
import com.example.restaurant.usecase.OrderUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MySqlOrderService implements OrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public MySqlOrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(OrderRequest request) {
        List<Product> productos = new ArrayList<>();
        double total = 0;

        for (Long idProducto : request.getProductos()) {
            Product producto = productRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + idProducto));
            productos.add(producto);
            total += producto.getPrice();
        }

        double descuento = request.getDescuento();
        if (descuento > 0.10) {
            throw new RuntimeException("El descuento no puede superar el 10%");
        }

        double totalConDescuento = total - (total * descuento);

        Order orden = new Order();
        orden.setNumeroMesa(request.getNumeroMesa());
        orden.setEntregado(request.isEntregado());
        orden.setDescuento(descuento);
        orden.setTotal(totalConDescuento);
        orden.setProductos(productos);

        return orderRepository.save(orden);
    }

    @Override
    public List<Order> getActiveOrders() {
        return orderRepository.findByEntregadoFalse();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order closeOrder(Long id, double coupon) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (order.isEntregado()) {
            throw new RuntimeException("Este pedido ya fue entregado");
        }

        if (coupon > 0.10) {
            throw new RuntimeException("Descuento máximo permitido es 10%");
        }

        double totalOriginal = order.getProductos().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        order.setDescuento(coupon);
        order.setTotal(totalOriginal - (totalOriginal * coupon));
        order.setEntregado(true);

        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (order.isEntregado()) {
            throw new RuntimeException("No se puede cancelar un pedido entregado");
        }

        orderRepository.deleteById(id);
        return order;
    }
}

