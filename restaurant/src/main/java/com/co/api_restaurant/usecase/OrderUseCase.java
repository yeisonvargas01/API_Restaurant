package com.co.api_restaurant.usecase;

import com.co.api_restaurant.model.Order;
import com.co.api_restaurant.model.Product;
import com.co.api_restaurant.service.OrderRepository;
import com.co.api_restaurant.service.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    public Order saveOrder(Order order) {
        order.setStatus("ACTIVO");
        order.setTotal(0.0);
        return orderRepository.saveOrder(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteOrder(id);
    }

    // Ver pedidos activos
    public List<Order> getActiveOrders() {
        List<Order> activeOrders = new ArrayList<>();
        for (Order order : orderRepository.getAllOrders()) {
            if ("ACTIVO".equals(order.getStatus())) {
                activeOrders.add(order);
            }
        }
        return activeOrders;
    }

    // Cerrar pedido (calcula total, aplica descuento, cambia estado)
    public Order closeOrder(Long orderId, double discount) {
        Optional<Order> optOrder = orderRepository.getOrderById(orderId);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
            if (!"ACTIVO".equals(order.getStatus())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "El pedido ya fue cerrado o cancelado."
                );
            }
            double total = 0.0;
            for (Long productId : order.getProductIds()) {
                Optional<Product> productOpt = productRepository.getProductById(productId);
                if (productOpt.isPresent()) {
                    total += productOpt.get().getPrice();
                }
            }
            if (discount > 10.0) {
                discount = 10.0;
            }
            double totalDescuento = total - (total * discount / 100.0);
            order.setDiscount(discount);
            order.setTotal(totalDescuento);
            order.setStatus("CERRADO");
            orderRepository.updateOrder(order);
            return order;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pedido no encontrado"
            );
        }
    }

    // Cancelar pedido (si está activo)
    public Order cancelOrder(Long orderId) {
        Optional<Order> optOrder = orderRepository.getOrderById(orderId);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
            if (!"ACTIVO".equals(order.getStatus())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "El pedido ya fue cerrado o cancelado."
                );
            }
            order.setStatus("CANCELADO");
            orderRepository.updateOrder(order);
            return order;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pedido no encontrado"
            );
        }
    }
}
