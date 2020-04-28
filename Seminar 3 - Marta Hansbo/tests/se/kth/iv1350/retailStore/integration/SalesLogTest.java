package se.kth.iv1350.retailStore.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.model.CashRegister;
import se.kth.iv1350.retailStore.model.Discount;
import se.kth.iv1350.retailStore.model.Payment;
import se.kth.iv1350.retailStore.model.SaleBuilder;

import static org.junit.jupiter.api.Assertions.*;

class SalesLogTest {
    SalesLog salesLog;
    SaleBuilder saleBuilder;
    Discount discount;
    Payment payment;
    GoodsDTO oneGoods;
    InventoryManager inventoryManager;
    CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        salesLog = new SalesLog();
        saleBuilder = new SaleBuilder();
        inventoryManager = new InventoryManager();
        oneGoods = new GoodsDTO(1, inventoryManager);
        saleBuilder.updateSale(oneGoods, 1);
        discount = new Discount();
        payment = new Payment(new Amount(100000),discount,saleBuilder);
        cashRegister = new CashRegister(new Amount(500000));
    }

    @AfterEach
    void tearDown() {
        saleBuilder = null;
        payment = null;
        discount = null;
        oneGoods = null;
        inventoryManager = null;
        salesLog = null;
        cashRegister = null;
    }

    @Test
    void logSale() {
        SaleDTO aSale = new SaleDTO(payment,saleBuilder,discount,cashRegister);
        SaleDTO anotherSale = new SaleDTO(payment,saleBuilder,discount, cashRegister);
        salesLog.logSale(aSale);
        assertEquals(salesLog.getListOfSales().get(0),aSale,"sale not logged");
        assertNotEquals(salesLog.getListOfSales().get(0), anotherSale,"wrong sale logged");
    }
}