package com.gildedrose.controller;

import com.gildedrose.dto.ItemDto;
import com.gildedrose.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody ItemDto dto) {
        itemService.addItem(dto);
        return ResponseEntity.ok("Item added");
    }

    @GetMapping("/items/update")
    public List<ItemDto> getUpdatedItems() {
        itemService.updateItemsQuality();
        return itemService.getAllItems();
    }


    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/items/search")
    public ResponseEntity<ItemDto> getItemByName(@RequestParam(name = "name") String name) {
        Optional<ItemDto> item = itemService.getItemByName(name);
        return item.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}


