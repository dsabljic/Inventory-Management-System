package com.inventory.backend.services;

import com.inventory.backend.dto.ItemDto;
import com.inventory.backend.entities.Item;
import com.inventory.backend.entities.ItemType;
import com.inventory.backend.repositories.ItemRepository;
import com.inventory.backend.repositories.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public ItemDto createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setQuantity(itemDto.getQuantity());
        item.setAvailableQuantity(itemDto.getAvailableQuantity());

        ItemType itemType = itemTypeRepository.findById(itemDto.getTypeId())
                .orElseThrow(() -> new RuntimeException("ItemType not found"));
        item.setItemType(itemType);

        Item savedItem = itemRepository.save(item);
        return convertToDto(savedItem);
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ItemDto> getItemById(Long id) {
        return itemRepository.findById(id).map(this::convertToDto);
    }

    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setQuantity(itemDto.getQuantity());
        item.setAvailableQuantity(itemDto.getAvailableQuantity());

        ItemType itemType = itemTypeRepository.findById(itemDto.getTypeId())
                .orElseThrow(() -> new RuntimeException("ItemType not found"));
        item.setItemType(itemType);

        Item updatedItem = itemRepository.save(item);
        return convertToDto(updatedItem);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);
    }

    private ItemDto convertToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setTypeId(item.getItemType().getId());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setAvailableQuantity(item.getAvailableQuantity());
        return itemDto;
    }
}