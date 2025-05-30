package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroMesa;
    private boolean entregado;
    private double total;
    private double descuento;

    @ManyToMany
    private List<Product> productos;
}

