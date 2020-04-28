package se.kth.iv1350.retailStore.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.controller.Controller;

import static org.junit.jupiter.api.Assertions.*;

class AccountingManagerTest {
    AccountingManager accountingManager;

    @BeforeEach
    void setUp() {
        accountingManager = new AccountingManager();
    }

    @AfterEach
    void tearDown() {
        accountingManager = null;
    }

    @Test
    void logAccountingInformation() {
        Controller controller = new Controller(new IntegratorCreator());
        controller.startNewSale();
        try {controller.registerGoods(1,1);}
        catch (Exception ignored){}
        SaleDTO saleDTO = new SaleDTO(controller.getFinalSale().getPayment(),controller.getCurrentSale(),controller.getCurrentDiscount(),new IntegratorCreator().getCashRegister());

        accountingManager.logAccountingInformation(saleDTO);

        assertNotNull(accountingManager.accountingInformation.get(0),
                "sale has not been logged");
    }
}