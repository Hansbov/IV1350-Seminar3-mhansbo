package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.IntegratorCreator;
import se.kth.iv1350.retailStore.integration.SaleDTO;
import se.kth.iv1350.retailStore.integration.SalesLog;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaleTerminatorTest {
    SaleTerminator saleTerminator;
    SaleBuilder saleBuilder;
    GoodsDTO theGoods;
    Discount discount;
    Payment payment;
    IntegratorCreator integratorCreator;

    @BeforeEach
    void setUp() {
        integratorCreator = new IntegratorCreator();
        saleBuilder = new SaleBuilder();
        saleTerminator = new SaleTerminator(integratorCreator);
        theGoods = new GoodsDTO(1,integratorCreator.getInventoryManager());
        saleBuilder.updateSale(theGoods, 1);
        discount = new Discount();
        payment = new Payment(new Amount(10000), discount, saleBuilder);

    }

    @AfterEach
    void tearDown() {
        saleBuilder = null;
        saleTerminator = null;
        theGoods = null;
        discount = null;
        payment = null;
    }

    @Test
    void endSale() {

        saleTerminator.endSale(new Amount(10000),saleBuilder,discount);

        String expChange = payment.getAmountChange().getStringAmount();
        assertEquals(expChange,saleTerminator.getPayment().getAmountChange().getStringAmount(),"error in final payment - amount change");

        String expPaid = payment.getAmountPaid().getStringAmount();
        assertEquals(expPaid,saleTerminator.getPayment().getAmountPaid().getStringAmount(),"error in final payment - amount paid");

        String expTotalAfterDiscount = saleBuilder.totalAfterDiscount(discount).getStringAmount();
        assertEquals(expTotalAfterDiscount,saleTerminator.getFinalSale().getFinalTotalAfterDiscount().getStringAmount(),"error in final salebuilder - amount after discount");

        String expDiscount = discount.getDiscountAmount().getStringAmount();
        assertEquals(expDiscount,saleTerminator.getFinalSale().getFinalDiscount().getDiscountAmount().getStringAmount(),"error in final discount");

        SaleDTO expSaleDTO = saleTerminator.getFinalSale();
        SaleDTO actualSaleDTO = integratorCreator.getSalesLog().getListOfSales().get(0);
        assertEquals(expSaleDTO,actualSaleDTO,"error in final sale data - SaleDTO");
    }
}