package com.gildedrose.service;

import com.gildedrose.dto.ItemDto;
import com.gildedrose.gildedrose.GildedRose;
import com.gildedrose.gildedrose.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final List<Item> itemList = new ArrayList<>();
    private final GildedRose gildedRose = new GildedRose(itemList.toArray(new Item[0]));

    public void addItem(ItemDto dto) {
        Item newItem = new Item(dto.getName(), dto.getSellIn(), dto.getQuality());
        itemList.add(newItem);
    }

    public List<ItemDto> getAllItems() {
        List<ItemDto> dtoList = new ArrayList<>();
        for (Item item : itemList) {
            dtoList.add(new ItemDto(item.getName(), item.getSellIn(), item.getQuality()));
        }
        return dtoList;
    }

    public Optional<ItemDto> getItemByName(String name) {
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(name)) {
                return Optional.of(new ItemDto(item.getName(), item.getSellIn(), item.getQuality()));
            }
        }
        return Optional.empty();
    }

    public void updateItemsQuality() {
        GildedRose gildedRose = new GildedRose(itemList.toArray(new Item[0]));
        gildedRose.updateQuality();
    }
}
