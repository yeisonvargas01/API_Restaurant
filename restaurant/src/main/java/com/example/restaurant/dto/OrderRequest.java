package com.example.restaurant.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private int numeroMesa;
    private boolean entregado;
    private double descuento;
    private List<Long> productos; // IDs de productos que se mandan desde Postman
}

