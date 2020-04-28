package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.InventoryManager;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    SaleBuilder testSale;
    Discount discount;
    Payment testPayment;
    Amount paid;

    @BeforeEach
    void setUp() {
        testSale = new SaleBuilder();
        InventoryManager inventoryManager = new InventoryManager();
        GoodsDTO testDTO = new GoodsDTO(1, inventoryManager);
        testSale.updateSale(testDTO,2);
        testDTO = new GoodsDTO(2, inventoryManager);
        testSale.updateSale(testDTO,1);
        discount = new Discount();
        paid = new Amount(50000);
        testPayment = new Payment(paid,discount,testSale);
    }

    @AfterEach
    void tearDown() {
        testSale = null;
        discount = null;
        testPayment = null;
        paid = null;
    }
    /**
     * testing the constructor since it contains logic
     * where exact change is rounded to coins:
     * exact change = paid - topay; exact change = this.change + rounding
     */
    @Test
    void Payment() {
        Amount expToPay = testSale.getRunningTotalInclVAT();
        Amount exactChange = paid.plus(expToPay.times(-1));
        Amount expRoundedChange = exactChange.roundedToCoins();
        Amount expRounding = exactChange.plus(expRoundedChange.times(-1));
        assertEquals(paid.getStringAmount(),testPayment.getAmountPaid().getStringAmount(),"wrong amount paid");
        assertEquals(expRounding.getStringAmount(),testPayment.getRounding().getStringAmount(),"wrong rounding of amount change");
        assertEquals(expRoundedChange.getStringAmount(), testPayment.getAmountChange().getStringAmount(),"wrong amount change");
    }
}