package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.Amount;

/**
 * represents any discount that a customer might be eligible for.
 * */
public class Discount {
    private Amount discountAmount;

    /**
     * creates a new discount.
     *
     */
    public Discount () {
        discountAmount = new Amount(0.0);
    }

    /**
     * Calculates the discount depending on the customer.
     * Not finished - temporary values below, will be replaced by calls a database of discount rules
     * @param currentCustomer the customer
     * @currentSale SaleBuilder to be added later
     */

    public void calculateDiscount(CustomerDTO currentCustomer) {
        switch (currentCustomer.getCustomerID()){
           case 1:
               discountAmount = new Amount(-5.00);
               break;
           case 2:
                discountAmount = new Amount(-2.00);
                break;
           case 3:
                discountAmount = new Amount(-10.00);
                break;
       }
    }

    public Amount getDiscountAmount() {
            return discountAmount;
    }


}
