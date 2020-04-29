package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.Amount;

/**
 * class representing a payment
 */
public class Payment {
    private final Amount amountPaid;
    private final Amount amountChange;
    private final Amount rounding;

    /**
     * Creator, sets the amount paid and amount change
     * logic: exact change = paid - toPay; exact change = this.change + rounding
     * @param amountPaid the amount of money that the customer has payed with
     * @param currentDiscount the discount concerning the current sale: Discount
     * @param currentSale the current sale: SaleBuilder.
     */
    public Payment(Amount amountPaid, Discount currentDiscount, SaleBuilder currentSale) {
        this.amountPaid = amountPaid;
        Amount toPay = currentSale.totalAfterDiscount(currentDiscount);
        Amount exactAmountChange = amountPaid.plus(toPay.times(-1));
        this.amountChange = exactAmountChange.roundedToCoins();
        rounding = exactAmountChange.plus(this.amountChange.times(-1));
    }

    /**
     *
     * @return Amount rounding
     */
    public Amount getRounding() {
        return rounding;
    }

    /**
     *
     * @return Amount amountChange
     */
    public Amount getAmountChange() {
        return amountChange;
    }

    /**
     *
     * @return Amount amountPaid
     */
    public Amount getAmountPaid() {
        return amountPaid;
    }
}
