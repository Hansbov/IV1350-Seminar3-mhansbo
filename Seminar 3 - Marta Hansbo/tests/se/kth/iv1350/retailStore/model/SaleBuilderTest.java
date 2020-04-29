package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.InventoryManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaleBuilderTest {
    SaleBuilder testSale;
    InventoryManager inventoryManager;
    @BeforeEach
    void setUp() {
        testSale = new SaleBuilder();
        inventoryManager = new InventoryManager();
    }

    @AfterEach
    void tearDown() {
        testSale = null;
    }

    @Test
    void updateSaleAttributes() {
        GoodsDTO testDTO1 = new GoodsDTO(1, inventoryManager);
        //expected1 adding nofItems1 items
        Amount expItemPriceinclVAT1 = testDTO1.getItemPriceInclVAT();
        Amount expItemVAT1 = testDTO1.getVATAmount();
        int nofItems1 = 2;
        Amount expRunningTotal1 = expItemPriceinclVAT1.times(nofItems1);
        Amount expRunningTotalVAT1 = expItemVAT1.times(nofItems1);
        //result and test1
        testSale.updateSale(testDTO1, nofItems1);
        assertEquals(expRunningTotal1.getStringAmount(),testSale.getRunningTotalInclVAT().getStringAmount(),"wrong running total after adding new item");
        assertEquals(expRunningTotalVAT1.getStringAmount(),testSale.getRunningTotalVAT().getStringAmount(),"wrong running total VAT after adding new item");

        //expected2 after adding nofItems2 more other items of the same type
        int nofItems2 = 3;
        Amount expRunningTotal2 = expItemPriceinclVAT1.times(nofItems1 + nofItems2);
        Amount expRunningTotalVAT2 = expItemVAT1.times(nofItems1 + nofItems2);
        //result and test2
        testSale.updateSale(testDTO1, nofItems2);
        assertEquals(expRunningTotal2.getStringAmount(),testSale.getRunningTotalInclVAT().getStringAmount(),"wrong running total after adding more of the same items ");
        assertEquals(expRunningTotalVAT2.getStringAmount(),testSale.getRunningTotalVAT().getStringAmount(),"wrong running total after adding more of the same items");

        //expected3 after subtracting nofItems3 items of the same type
        int nofItems3 = -2;
        Amount expRunningTotal3 = expItemPriceinclVAT1.times(nofItems1 + nofItems2 + nofItems3);
        Amount expRunningTotalVAT3 = expItemVAT1.times(nofItems1 + nofItems2 + nofItems3);
        //result and test3
        testSale.updateSale(testDTO1, nofItems3);
        assertEquals(expRunningTotal3.getStringAmount(),testSale.getRunningTotalInclVAT().getStringAmount(),"wrong running total after subtracting items");
        assertEquals(expRunningTotalVAT3.getStringAmount(),testSale.getRunningTotalVAT().getStringAmount(),"wrong running total VAT after subtracting items");
    }

    @Test
    void updateSale(){
        // testing output of method updateSale
        GoodsDTO testDTO = new GoodsDTO(1, inventoryManager);
        RecentlyAddedDTO result = testSale.updateSale(testDTO,1);

        assertEquals(testDTO.getItemDescription(),result.getItemDescription(),"wrong item description returned");
        assertEquals(testSale.getRunningTotalInclVAT().getStringAmount(), result.getRunningTotal().getStringAmount(),"wrong running total VAT returned");
        assertEquals(testDTO.getItemPriceInclVAT().getStringAmount(), result.getPrice().getStringAmount(),"wrong price returned");
    }


}