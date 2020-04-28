package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.model.Discount;
import se.kth.iv1350.retailStore.model.RecentlyAddedDTO;
import se.kth.iv1350.retailStore.integration.Amount;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * view represents the interaction between the user and the program
 */
public class View {
    private Controller controller;

    /**
     * creator creates a view with a reference to the controller.
     * @param controller a reference to the controller.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * a temporary representation of the things a user could choose to do.
     */
    public void runScenario() {
        System.out.println("Comments refer to the Requirements Specification ");
        System.out.println("1. Customer arrives at POS with goods to purchase");

        controller.startNewSale();
        System.out.println("2. Cassier starts a new sale ");
        System.out.println();

        System.out.println("3-5 + 3-4bc: Cassier enters item (new or more of the same),");
        System.out.println("program records and presents them ...");
        int itemID = 1;
        int numberOfItems = 1;
        try {
            RecentlyAddedDTO theItem = controller.registerGoods(itemID, numberOfItems);
            System.out.println("Adding Item:" + theItem);
        }
        catch (Exception e){
            exceptionHandler();
        }
        itemID = 2;
        try {
            RecentlyAddedDTO theItem = controller.registerGoods(itemID, numberOfItems);
            System.out.println("Adding Item:" + theItem);
        }
        catch (Exception e){
            exceptionHandler();
        }
        itemID = 1;
        numberOfItems = 2;
        try {
            RecentlyAddedDTO theItem = controller.registerGoods(itemID, numberOfItems);
            System.out.println("Adding Item:" + theItem);
        }
        catch (Exception e){
            exceptionHandler();
        }
        System.out.println();

        System.out.println("6-8: No more items to buy - Cashier asks for total price:");
        Amount toPay = controller.showWhatToPay();
        System.out.println("9-10. The total price is: "+toPay.getStringAmount());
        System.out.println();

        System.out.println("9a. Checking for discount...");
        controller.requestDiscount(2);
        if (controller.getCurrentDiscount().getDiscountAmount().getStringAmount() !="0.00")
            System.out.println("Discount found. New Total: " + controller.showWhatToPay().getStringAmount() + " kr");
        else
            System.out.println("No discount found.");
        System.out.println();

        System.out.println("11-12: Now we will finalize the sale.");
        System.out.println("16. Printing Receipt...");
        System.out.println();
        System.out.println("/////////////////////////////////////");

        String cashBalanceBeforeSale = controller.showCashBalance().getStringAmount();
        Amount amountPaid = new Amount(300.00);
        Amount change = controller.finalizeSale(amountPaid);
        String cashBalanceAfterSale = controller.showCashBalance().getStringAmount();

        System.out.println("/////////////////////////////////////");
        System.out.println();

        System.out.println("13-14. Sale has been logged." );
        System.out.println("15. Cash balance before sale: " + cashBalanceBeforeSale + " kr.");
        System.out.println("Cash balance after sale: " + cashBalanceAfterSale + " kr.");
    }

    /**
     * handles an exception where user must add item manually.
     * OBS! ignore this for now.
     */
    private void exceptionHandler(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the following information manually:");
        System.out.println("ItemID:");
        int itemID = sc.nextInt();
        System.out.println("price ex. VAT:");
        int priceInIntegerForm = sc.nextInt();
        System.out.println("VAT-Rate:");
        int VATRate = sc.nextInt();
        System.out.println("Item description:");
        String itemDescription = sc.nextLine();
        System.out.println("Number of items:");
        int numberOfItems = sc.nextInt();
        Amount price = new Amount(priceInIntegerForm);

        GoodsDTO itemToAdd = new GoodsDTO(itemDescription,itemID,price,VATRate);
        RecentlyAddedDTO itemAdded = controller.registerUnknownGoods(itemToAdd, numberOfItems);
        System.out.println();
        System.out.println("Adding Item:" + itemAdded);
    }
}
