package com.inventory.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item_types")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
