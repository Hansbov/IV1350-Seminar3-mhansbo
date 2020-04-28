package se.kth.iv1350.retailStore.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.IntegratorCreator;
import se.kth.iv1350.retailStore.model.RecentlyAddedDTO;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller;

    @BeforeEach
    void setUp() {
        IntegratorCreator integratorCreator = new IntegratorCreator();
        controller = new Controller(integratorCreator);
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void startNewSale() {
        controller.startNewSale();
        assertNotNull(controller.getCurrentSale(), "current sale is null");
        assertNotNull(controller.getCurrentDiscount(), "currentDiscount is null");
        assertNotNull(controller.getFinalSale(), "finalSale is null");
    }

    @Test
    void registerGoods() throws Exception {
        try {
            RecentlyAddedDTO itemAdded = controller.registerGoods(1, 1);
            assertNotNull(itemAdded, "itemAdded is null");
        }
        catch (Exception ignored) { }

        try {
            RecentlyAddedDTO otherItem = controller.registerGoods(22,1);
            assertNull(otherItem, "itemAdded is not null");
        }
        catch (Exception ignored) { }

    }

    @Test
    void requestDiscount() {
        controller.startNewSale();
        try { controller.registerGoods(1, 1); }
        catch (Exception ignored) {}

        String totalWithDiscount = controller.requestDiscount(2).getStringAmount();
        String totalWithoutDiscount = controller.getCurrentSale().getRunningTotalInclVAT().getStringAmount();

        assertNotEquals(totalWithDiscount, totalWithoutDiscount,
                "Total is the same with or without discount.");
    }

    @Test
    void showWhatToPay() {
        controller.startNewSale();
        try { controller.registerGoods(1, 1); }
        catch (Exception ignored) {}

        String whatToPay = controller.showWhatToPay().getStringAmount();
        String runningTotal = controller.getCurrentSale().getRunningTotalInclVAT().getStringAmount();

        assertEquals(whatToPay, runningTotal,
                "runningTotal and whatToPay are not equal.");

        controller.requestDiscount(1);
        whatToPay = controller.showWhatToPay().getStringAmount();
        runningTotal = controller.getCurrentSale().getRunningTotalInclVAT().getStringAmount();

        assertNotEquals(whatToPay, runningTotal,
                "runningTotal and whatToPay are equal.");
    }

    @Test
    void finalizeSale() {
        controller.startNewSale();
        try { controller.registerGoods(1, 1); }
        catch (Exception ignored) {}

        String someChange = controller.finalizeSale(new Amount(100.)).getStringAmount();
        String someAmount = new Amount(100.).getStringAmount();
        String otherAmount = new Amount(30.).getStringAmount();
      assertNotEquals(someChange, someAmount,"Amount change is equal to amount paid");
      assertEquals(someChange, otherAmount, "Amount change is not 30");

      controller.startNewSale();
      try { controller.registerGoods(1, 1); }
      catch (Exception ignored) {}

      String someOtherChange = controller.finalizeSale(new Amount(60.)).getStringAmount();
      String moreAmount = new Amount(-10.).getStringAmount();

      assertEquals(someOtherChange, moreAmount, "Amount change is not -10");

    }
}