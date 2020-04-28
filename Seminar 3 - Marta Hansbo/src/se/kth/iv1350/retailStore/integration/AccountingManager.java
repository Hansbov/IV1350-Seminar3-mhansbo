package se.kth.iv1350.retailStore.integration;

import java.util.ArrayList;

/**
 * a go between between the program and the external accounting system.
 */
public class AccountingManager {
    ArrayList<SaleDTO> accountingInformation;

    /**
     * creator
     */
    public AccountingManager() {
        accountingInformation = new ArrayList<SaleDTO>();

    }
    /**
     * logs information to the external accounting system based on data package, would
     * in reality pick out relevant information and log this in an external accounting system.
     * @param finalSale data package containing information about sale.
     */
    public void logAccountingInformation(SaleDTO finalSale) {
        accountingInformation.add(finalSale);
    }

    /**
     * contains the original amount of money in the cashRegister
     * used when creating a new CashRegister
     * @return initial amount of money in the CashRegister
     */
    public Amount getCashRegInitAmount(){return new Amount(8349.00);}
}
