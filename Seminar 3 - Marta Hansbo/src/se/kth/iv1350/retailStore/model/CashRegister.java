package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.Amount;

/**
 * represents the amount of cash in the cash register
 */
public class CashRegister {
    private Amount cashBalance;

    /**
     * creates instance of Cash Register, the cash balance should be able to be set by the
     * cashier, but for this assignment I ignore that.
     */
    public CashRegister(Amount cashBalance) {
        this.cashBalance = cashBalance;
    }

    /**
     * updates the current cash balance and returns the change.
     * @param currentPayment the payment that the customer has given in cash
     * @return the amount of change
     */
    public Amount updateCashBalance(Payment currentPayment) {
         cashBalance = cashBalance.plus(currentPayment.getAmountPaid()).plus(currentPayment.getAmountChange().times(-1));
         return cashBalance;
    }

    public Amount getCashBalance() {
        return cashBalance;
    }
}
