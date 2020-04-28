package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.IntegratorCreator;
import se.kth.iv1350.retailStore.integration.SaleDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        IntegratorCreator integratorCreator = new IntegratorCreator();
        Controller controller = new Controller(integratorCreator);
        controller.startNewSale();
        try {controller.registerGoods(1,1);}
        catch (Exception ignored){}
        Payment payment = new Payment(new Amount(100.),controller.getCurrentDiscount(),controller.getCurrentSale());
        saleDTO = new SaleDTO(payment,controller.getCurrentSale(),controller.getCurrentDiscount(),integratorCreator.getCashRegister());

    }

    @AfterEach
    void tearDown() {
        saleDTO = null;
    }

    @Test
    void testToString() {
        Receipt receipt = new Receipt(saleDTO);
        String result = receipt.toString();

        String expTimeAndDate = receipt.getTimeAndDate();
        assertTrue(result.contains(expTimeAndDate),"wrong time and date ");

        for (Goods someG : receipt.getFinalGoods()){
            String expItemDescription = someG.getGoodsDescription().getItemDescription();
            assertTrue(result.contains(expItemDescription),"wrong item description ");
        }

        String expFinalTotal = receipt.getFinalTotal().getStringAmount();
        assertTrue(result.contains(expFinalTotal),"wrong final total ");

        String expFinalTotalVAT = receipt.getFinalTotalVAT().getStringAmount();
        assertTrue(result.contains(expFinalTotalVAT),"wrong final total VAT ");

        String expDiscount = receipt.getFinalDiscount().getStringAmount();
        assertTrue(result.contains(expDiscount),"wrong discount ");

        String expFinalTotalAfterDiscount = receipt.getFinalTotalAfterDiscount().getStringAmount();
        assertTrue(result.contains(expFinalTotalAfterDiscount),"wrong final total after discount ");

        String expAmountPaid = receipt.getAmountPaid().getStringAmount();
        assertTrue(result.contains(expAmountPaid),"wrong amount paid ");

        String expAmountChange = receipt.getAmountChange().getStringAmount();
        assertTrue(result.contains(expAmountChange),"wrong amount change ");

        String expRounding = receipt.getRounding().getStringAmount();
        assertTrue(result.contains(expRounding),"wrong rounding");

    }
}