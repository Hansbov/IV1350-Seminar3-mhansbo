package se.kth.iv1350.retailStore.integration;

import se.kth.iv1350.retailStore.model.Goods;
import se.kth.iv1350.retailStore.model.Receipt;
import java.io.*;

/**
 * represent the connection with an external printer.
 */
public class Printer {

    public Printer() {
    }

    /**
     * prints a receipt
     * @param receipt the receipt to be printed
     */
    public void printReceipt(Receipt receipt) {
        System.out.print(receipt);
    }
}