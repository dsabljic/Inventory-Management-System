package com.inventory.backend.dto;

import com.inventory.backend.entities.ItemType;
import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Long typeId;
    private Integer quantity;
    private Integer availableQuantity;
}