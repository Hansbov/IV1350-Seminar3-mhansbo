package se.kth.iv1350.retailStore.integration;
import se.kth.iv1350.retailStore.model.*;

/**
 * Represents an integrator creator that creates the integration
 * classes and keeps them as attributes.
 */
public class IntegratorCreator {
    private final CashRegister cashRegister;
    private final InventoryManager inventoryManager;
    private final AccountingManager accountingManager;
    private final SalesLog salesLog;
    private final Printer printer;

    /**
     * creator, creates the instances needed for integration with other systems
     */
    public IntegratorCreator() {
        this.salesLog = new SalesLog();
        this.printer = new Printer();
        this.inventoryManager = new InventoryManager();
        this.accountingManager = new AccountingManager();
        this.cashRegister = new CashRegister(accountingManager.getCashRegInitAmount());
    }

    /**
     *
     * @return InventoryManager
     */
    public InventoryManager getInventoryManager () {
        return inventoryManager;
    }

    /**
     *
     * @return AccountingManager
     */
    public AccountingManager getAccountingManager(){
        return accountingManager;
    }

    /**
     *
     * @return SalesLog
     */
    public SalesLog getSalesLog(){
        return salesLog;
    }

    /**
     *
     * @return Printer
     */
    public Printer getPrinter(){
        return printer;
    }

    /**
     *
     * @return CashRegister
     */
    public CashRegister getCashRegister(){
        return cashRegister;
    }
}
