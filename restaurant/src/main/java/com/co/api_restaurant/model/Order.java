package com.co.api_restaurant.model;

import java.util.List;

public class Order {
    private Long id;
    private int tableNumber;
    private List<Long> productIds; // Lista de IDs de productos
    private String status; // "ACTIVO", "CERRADO", "CANCELADO"
    private double discount; // porcentaje 0-10
    private double total; // calculado al cerrar pedido

    public Order() {}

    public Order(Long id, int tableNumber, List<Long> productIds, String status, double discount, double total) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.productIds = productIds;
        this.status = status;
        this.discount = discount;
        this.total = total;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}


