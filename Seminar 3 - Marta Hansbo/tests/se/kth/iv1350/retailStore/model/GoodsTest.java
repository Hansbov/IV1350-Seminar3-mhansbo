package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.InventoryManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GoodsTest {
private Goods theGoods;
private int numberOfItems;
    @BeforeEach
    void setUp() {
        numberOfItems = 2;
        InventoryManager inventoryManager = new InventoryManager();
        GoodsDTO theItems = new GoodsDTO(1, inventoryManager);
        theGoods = new Goods(theItems, numberOfItems);
    }

    @AfterEach
    void tearDown() {
        numberOfItems = 0;
        theGoods = null;
    }

    @Test
    void addNumberOfItems() {
        theGoods.addNumberOfItems(1);
        numberOfItems = numberOfItems + 1;
        assertEquals(theGoods.getNumberOfItems(),numberOfItems,"wrong number of items");
        theGoods.addNumberOfItems(5);
        numberOfItems = numberOfItems + 3;
        assertNotEquals(theGoods.getNumberOfItems(), numberOfItems,"wrong number of items");
    }

    @Test
    void subNumberOfItems() {
        theGoods.subNumberOfItems(1);
        numberOfItems = numberOfItems - 1;
        assertEquals(theGoods.getNumberOfItems(), numberOfItems,"wrong number of items");
        theGoods.subNumberOfItems(2);
        numberOfItems = numberOfItems - 2;
        assertEquals(theGoods.getNumberOfItems(), numberOfItems,"wrong number of items");
        theGoods.subNumberOfItems(1);
        assertNotEquals(theGoods.getNumberOfItems(), numberOfItems,"wrong number of items");
    }
}