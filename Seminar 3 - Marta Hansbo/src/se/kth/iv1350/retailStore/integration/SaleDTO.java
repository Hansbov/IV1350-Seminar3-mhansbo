package se.kth.iv1350.retailStore.integration;

import se.kth.iv1350.retailStore.model.*;

import java.util.Date;

/**
 * a data package containing information about the finalized sale
 */
public class SaleDTO {

    private final Cart finalCart;
    private final Amount finalTotal;
    private final Amount finalTotalVAT;
    private final Amount finalTotalAfterDiscount;
    private final Discount finalDiscount;
    private final String timeAndDate;
    private final Payment finalPayment;
    private final String cashBalanceAfterSale;

    /**
     * Creator
     * @param currentPayment the payment of the sale
     * @param currentSale the current SaleBuilder
     * @param currentDiscount the current discount
     * @param cashRegister the cashRegister after the current sale
     */

    public SaleDTO(Payment currentPayment, SaleBuilder currentSale, Discount currentDiscount, CashRegister cashRegister) {
        finalCart = currentSale.getCurrentCart();
        finalTotal = currentSale.getRunningTotalInclVAT();
        finalTotalVAT = currentSale.getRunningTotalVAT();
        finalTotalAfterDiscount = currentSale.totalAfterDiscount(currentDiscount);
        finalDiscount = currentDiscount;
        finalPayment = currentPayment;
        Date timeAndDate = new Date();
        this.timeAndDate = timeAndDate.toString();
        cashBalanceAfterSale = cashRegister.getCashBalance().getStringAmount();
    }

    public Cart getFinalCart() {
        return finalCart;
    }
    public Amount getFinalTotal() {
        return finalTotal;
    }
    public Amount getFinalTotalVAT() {
        return finalTotalVAT;
    }
    public Amount getFinalTotalAfterDiscount() {
        return finalTotalAfterDiscount;
    }
    public Discount getFinalDiscount() {
        return finalDiscount;
    }
    public String getTimeAndDate() {
        return timeAndDate;
    }
    public Payment getFinalPayment() {
        return finalPayment;
    }
    public String getCashBalanceAfterSale() { return cashBalanceAfterSale; }

}
