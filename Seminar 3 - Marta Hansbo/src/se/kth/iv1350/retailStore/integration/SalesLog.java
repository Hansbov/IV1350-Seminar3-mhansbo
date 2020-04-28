package se.kth.iv1350.retailStore.integration;

import java.util.ArrayList;

/**
 * represents the log of daily sales
 */
public class SalesLog {

    private ArrayList<SaleDTO> listOfSales;

    /**
     * creator
     */
    public SalesLog() {
        listOfSales = new ArrayList<SaleDTO>();
    }

    /**
     * logs the sale in the SalesLog
     * @param finalSale the sale to be logged
     */
    public void logSale(SaleDTO finalSale) {
        listOfSales.add(finalSale);
    }

    public ArrayList<SaleDTO> getListOfSales() {
        return listOfSales;
    }
}
