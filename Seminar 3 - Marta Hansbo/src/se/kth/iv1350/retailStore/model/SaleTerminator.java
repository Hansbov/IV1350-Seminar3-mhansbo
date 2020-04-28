package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.IntegratorCreator;
import se.kth.iv1350.retailStore.integration.SaleDTO;

/**
 * represents the part of the program that terminates a sale.
 */
public class SaleTerminator {
    private SaleDTO finalSale;
    private Receipt receipt;
    private Payment payment;
    private IntegratorCreator integratorCreator;

    /**
     *  creates a new instance of SaleTerminator.
     * @param integrator the IntegratorCreator needed for SaleTerminator to be able to
     *                   contact external systems.
     */
    public SaleTerminator(IntegratorCreator integrator) {
        this.integratorCreator = integrator;
    }

    /**
     * stores information about sale, prints receipt and returns a payment
     * @param amountPaid the amount the customer has paid in cash
     * @param currentSale the current SaleBuilder
     * @param currentDiscount the current Discount
     */
    public void endSale(Amount amountPaid, SaleBuilder currentSale, Discount currentDiscount) {
        payment = new Payment(amountPaid,currentDiscount, currentSale);
        CashRegister cashRegister = integratorCreator.getCashRegister();
        cashRegister.updateCashBalance(payment);
        finalSale = new SaleDTO(payment, currentSale, currentDiscount,cashRegister);
        receipt = new Receipt(finalSale);
        receipt.printReceipt(receipt);
        integratorCreator.getSalesLog().logSale(finalSale);
        integratorCreator.getAccountingManager().logAccountingInformation(finalSale);
        integratorCreator.getInventoryManager().logInventoryData(finalSale);
    }

    public Payment getPayment() {
        return payment;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public SaleDTO getFinalSale() {
        return finalSale;
    }

}
