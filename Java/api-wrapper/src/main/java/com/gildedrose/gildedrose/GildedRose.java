package com.gildedrose.gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (!isAgedBrie(item) && !isBackstagePass(item)) {
            degradeQuality(item);
        } else {
            increaseQuality(item);

            if (isBackstagePass(item)) {
                if (item.sellIn < 11) {
                    increaseQuality(item);
                }
                if (item.sellIn < 6) {
                    increaseQuality(item);
                }
            }
        }

        if (!isSulfuras(item)) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }

    private void degradeQuality(Item item) {
        if (item.quality > 0 && !isSulfuras(item)) {
            item.quality--;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void handleExpiredItem(Item item) {
        if (!isAgedBrie(item)) {
            if (!isBackstagePass(item)) {
                degradeQuality(item);
            } else {
                item.quality = 0;
            }
        } else {
            increaseQuality(item);
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}
