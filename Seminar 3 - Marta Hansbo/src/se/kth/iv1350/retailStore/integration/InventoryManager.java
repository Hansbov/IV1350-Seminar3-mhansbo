package se.kth.iv1350.retailStore.integration;

import se.kth.iv1350.retailStore.model.Cart;

import java.util.ArrayList;

/**
 * Represents the part of the program that would interact with an external system.
 * As we will not have an external system, some hardcoded data has been added.
 *
 * OBS: For now itemID available is numbers 1-5!
 */
public class InventoryManager {
    ArrayList<Cart> inventoryInformation;

    /**
     * Creates a new instance.
     */
    public InventoryManager() {
        inventoryInformation = new ArrayList<Cart>();

    }

    /**
     * Would check if an Item exists in the external database,
     * for now substituted with hardcoded example.
     *
     * @param itemID The id of the item, for now only 1-5.
     * @return Boolean representing yes/no item exists.
     */
    public boolean checkAvailable(int itemID) {
        boolean existsInSystem;

        if (itemID == 1 ||
            itemID == 2 ||
            itemID == 3 ||
            itemID == 4 ||
            itemID == 5){

            existsInSystem = true;
        }

        else
            existsInSystem = false;

        return existsInSystem;
    }

    /**
     * Would pick out the correct information and log that in an external inventory system.
     * for now replaced with a arraylist.
     * @param finalSale a data package containing information about the finalized sale.
     */
    public void logInventoryData(SaleDTO finalSale) {
        inventoryInformation.add(finalSale.getFinalCart());
    }

    /**
     * Gets the price of the item.
     *
     * @param itemID id of the item, for now only 1-5.
     * @return Amount the price of the item.
     */
    public Amount getPrice (int itemID) {
        double amountInt = 0;
        switch (itemID){
            case 1:
                amountInt = 55.92;
                break;
            case 2:
                amountInt = 22.32;
                break;
            case 3:
                amountInt = 15.09;
                break;
            case 4:
                amountInt = 21.52;
                break;
            case 5:
                amountInt = 32.54;
                break;
        }
        Amount price = new Amount(amountInt);
        return price;
    }

    /**
     * gets the VAT-rate of the item
     * @param itemID id of the item, for now only 1-5.
     * @return double the VAT-rate of the item
     */
    public int getVATRate (int itemID) {
        int vatRate = 0;
        switch (itemID){
            case 1:
                vatRate = 25;
                break;
            case 2:
                vatRate = 25;
                break;
            case 3:
                vatRate = 12;
                break;
            case 4:
                vatRate = 25;
                break;
            case 5:
                vatRate = 6;
                break;
        }
        return vatRate;
    }

    /**
     * Gets the item description
     *
     * @param itemID id of the item, for now only 1-5.
     * @return String a short description of the item
     */
    public String getItemDescription (int itemID) {

        StringBuilder itemDescriptionCreator = new StringBuilder();

        switch (itemID){
            case 1:
                itemDescriptionCreator.append("Torskfilé 350g");
                break;
            case 2:
                itemDescriptionCreator.append("Tomatketchup 1kg Heinz");
                break;
            case 3:
                itemDescriptionCreator.append("Kvarg Naturell 0,2% 500g Lindahls");
                break;
            case 4:
                itemDescriptionCreator.append("Fruktdryck Mango 1l Proviva");
                break;
            case 5:
                itemDescriptionCreator.append("Ägg Frigående 10-p");
                break;
        }
        return itemDescriptionCreator.toString();
    }
}
