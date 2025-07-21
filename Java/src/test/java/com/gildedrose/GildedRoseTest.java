package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    // Tests for normal items
    @Test
    void testNormalItemQualityDecreases() {
        Item[] items = new Item[] { new Item("Normal Item", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void testNormalItemQualityNeverNegative() {
        Item[] items = new Item[] { new Item("Normal Item", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    // Tests for Aged Brie
    @Test
    void testAgedBrieQualityIncreases() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void testAgedBrieQualityNeverExceeds50() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void agedBrieQualityIncreasesCorrectlyWhenBelow50() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }

    // Tests for Backstage passes
    @Test
    void testBackstagePassesQualityIncreasesBy2When10DaysOrLess() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void testBackstagePassesQualityDropsTo0AfterConcert() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    // Tests for Sulfuras
    @Test
    void sulfurasNeverChangesQuality() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        GildedRose app = new GildedRose(new Item[] { sulfuras });
        app.updateQuality();
        assertEquals(80, sulfuras.quality);
    }

    @Test
    public void sulfurasNeverExpires() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        GildedRose app = new GildedRose(new Item[] { sulfuras });
        app.updateQuality();
        assertEquals(10, sulfuras.sellIn);
    }

}
