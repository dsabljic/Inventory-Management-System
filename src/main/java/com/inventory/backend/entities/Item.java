package com.inventory.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ItemType itemType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer availableQuantity;
}