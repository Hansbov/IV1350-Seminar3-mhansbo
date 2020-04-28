package se.kth.iv1350.retailStore.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.model.Cart;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {
    private InventoryManager inventoryManager;

    @BeforeEach
    void setUp() {
        inventoryManager = new InventoryManager();
    }

    @AfterEach
    void tearDown() {
        inventoryManager = null;
    }

    @Test
    void testCheckAvailable() {
        int itemID = 1;
        int itemIDDoesNotExist = 22;

        boolean result = inventoryManager.checkAvailable(itemID);
        assertTrue(result, "Item does not exist");


        boolean otherResult = inventoryManager.checkAvailable(itemIDDoesNotExist);
        assertFalse(otherResult, "Item does exist");
    }


    @Test
    void testLogInventoryData() {
        Controller controller = new Controller(new IntegratorCreator());
        controller.startNewSale();
        try {controller.registerGoods(1,1);}
        catch (Exception ignored){}
        SaleDTO saleDTO = new SaleDTO(controller.getFinalSale().getPayment(),controller.getCurrentSale(),controller.getCurrentDiscount(),new IntegratorCreator().getCashRegister());

        inventoryManager.logInventoryData(saleDTO);
        Cart loggedCart = inventoryManager.inventoryInformation.get(0);

        assertNotNull(loggedCart, "Cart has not been logged");
    }


}