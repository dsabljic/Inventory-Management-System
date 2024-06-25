package com.inventory.backend.controllers;

import com.inventory.backend.dto.ItemTypeDto;
import com.inventory.backend.services.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/item-types")
public class ItemTypeController {

    @Autowired
    private ItemTypeService itemTypeService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ItemTypeDto> createItemType(@RequestBody ItemTypeDto itemTypeDto) {
        System.out.println("-----------------------------------------------");
        System.out.println("Received request to create item type: " + itemTypeDto.getName());
        System.out.println("-----------------------------------------------");
        return ResponseEntity.ok(itemTypeService.createItemType(itemTypeDto));
    }

    @GetMapping
    public ResponseEntity<List<ItemTypeDto>> getAllItemTypes() {
        return ResponseEntity.ok(itemTypeService.getAllItemTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemTypeDto> getItemTypeById(@PathVariable Long id) {
        return itemTypeService.getItemTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ItemTypeDto> updateItemType(@PathVariable Long id, @RequestBody ItemTypeDto itemTypeDto) {
        return ResponseEntity.ok(itemTypeService.updateItemType(id, itemTypeDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteItemType(@PathVariable Long id) {
        itemTypeService.deleteItemType(id);
        return ResponseEntity.noContent().build();
    }
}