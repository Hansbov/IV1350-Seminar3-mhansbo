package se.kth.iv1350.retailStore.controller;

import se.kth.iv1350.retailStore.integration.*;
import se.kth.iv1350.retailStore.model.*;

/**
 * controller class
 */
public class Controller {
    private SaleBuilder currentSale;
    private Discount currentDiscount;
    private SaleTerminator finalSale;
    private final IntegratorCreator integrator;

    /**
     * the creator
     * @param integratorCreator helps with integration to external systems
     */
    public Controller(IntegratorCreator integratorCreator) {
        integrator = integratorCreator;
    }

    /**
     * Start the Classes needed for a new sale
     */
    public void startNewSale() {
        currentDiscount = new Discount();
        currentSale = new SaleBuilder();
        finalSale = new SaleTerminator(integrator);

    }

    /**
     * Method that checks that item exists in external database and adds it to SaleBuilder if it does.
     * @param itemID the id of the item to add
     * @param numberOfItems the amount of items to add
     * @return a data package to display
     */
    public RecentlyAddedDTO registerGoods(int itemID, int numberOfItems) throws Exception
    {
        InventoryManager invManager = integrator.getInventoryManager();
        boolean isInInventory = invManager.checkAvailable(itemID);
        if(isInInventory) {
            GoodsDTO itemToAdd = new GoodsDTO(itemID, invManager);
            return currentSale.updateSale(itemToAdd, numberOfItems);
        }
        else {
            throw new Exception("Item does not exist in the external inventory system.");
        }
    }

    /**
     * Adds an manually entered item to SaleBuilder.
     * @param itemToAdd the item information to be added
     * @param numberOfItems the number of items to add
     * @return a data package to display
     */
    public RecentlyAddedDTO registerUnknownGoods(GoodsDTO itemToAdd, int numberOfItems){
        return currentSale.updateSale(itemToAdd, numberOfItems);
    }

    /**
     * Adds discount if available.
     * @param customerID identification of customer
     * @return the current price including discount.
     */
    public Amount requestDiscount(int customerID) {
        CustomerDTO currentCustomer = new CustomerDTO( customerID);
        currentDiscount.calculateDiscount(currentCustomer);
        return showWhatToPay();
    }

    /**
     * calculates total price including any discounts
     * @return the total price of the current sale
     */
    public Amount showWhatToPay() {
        return currentSale.totalAfterDiscount(currentDiscount);
    }

    /**
     * Ends the current sale.
     * @param amountPaid the amount a customer has paid in cash
     * @return the amount to be given as change to the customer.
     */
    public Amount finalizeSale(Amount amountPaid) {
        finalSale.endSale(amountPaid, currentSale, currentDiscount);
        currentSale = null;
        return finalSale.getPayment().getAmountChange();
    }

    /**
     * Shows cash balance.
     * @return Amount returns the current balance in the CashRegister
     */
    public Amount showCashBalance() {
        return integrator.getCashRegister().getCashBalance();
    }

    /**
     * gets currentSale
     * @return SaleBuilder currentSale
     */
    public SaleBuilder getCurrentSale(){
        return currentSale;
    }

    /**
     * returns currentDiscount
     * @return Discount currentDiscount
     */
    public Discount getCurrentDiscount(){
        return currentDiscount;
    }

    /**
     * returns finalSale
     * @return SaleTerminator finalSale
     */

    public SaleTerminator getFinalSale(){
        return finalSale;
    }

    /**
     * returns integrator
     * @return IntegratorCreator integrator
     */
    public IntegratorCreator getIntegrator(){
        return integrator;
    }
}
