package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.InventoryManager;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {
    CashRegister cashRegister;
    Discount discount;
    SaleBuilder saleBuilder;
    Payment payment;
    Amount cost;

    @BeforeEach
    void setUp() {
        InventoryManager inventoryManager = new InventoryManager();
        cashRegister = new CashRegister(new Amount(1500));
        saleBuilder = new SaleBuilder();
        GoodsDTO itemToAdd = new GoodsDTO(2, inventoryManager);
        cost = itemToAdd.getItemPriceInclVAT();
        saleBuilder.updateSale(itemToAdd, 3);
        discount = new Discount();
        payment = new Payment(new Amount(10000),discount,saleBuilder);
    }

    @AfterEach
    void tearDown() {
        cashRegister = null;
        saleBuilder = null;
        discount = null;
        payment = null;
    }

    @Test
    void updateCashBalance() {
        Amount amountBefore = cashRegister.getCashBalance();
        Amount diff = payment.getAmountPaid().plus(payment.getAmountChange().times(-1));
        Amount expAmountAfter = amountBefore.plus(diff);
        Amount actualAmountAfter = cashRegister.updateCashBalance(payment);
        assertEquals(expAmountAfter.getStringAmount(), actualAmountAfter.getStringAmount(),"wrong cash balance");
    }
}