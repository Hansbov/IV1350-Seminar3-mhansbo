package se.kth.iv1350.retailStore.integration;

/**
 * represents a package of information about an item
 */
public class GoodsDTO {
    private final Amount price;
    private final Amount itemPriceInclVAT;
    private final int VATRate;
    private final Amount VATamount;
    private final String itemDescription;
    private final int itemID;
    private final boolean existsInDatabase;

    /**
     * Creator of GoodsDTO, creates a data transfer object containing information of a Goods
     * @param itemID the item ID of the item we want to gather information about
     * @param inventoryManager a reference to the inventoryManager that
     *                         we need to be able to gather information
     */
    public GoodsDTO(int itemID, InventoryManager inventoryManager) {

        existsInDatabase = inventoryManager.checkAvailable(itemID);
        this.itemID = itemID;
        this.price = inventoryManager.getPrice(itemID);
        VATRate = inventoryManager.getVATRate(itemID);
        VATamount = price.takePercent(VATRate);
        itemPriceInclVAT = price.plus(VATamount);
        itemDescription = inventoryManager.getItemDescription(itemID);

    }

    /**
     * If the item does not exist in the external inventory system, we need a way to create a
     * data package containing the correct information.
     * @param itemDescription in-data from user, String information about item
     * @param itemID in-data from user, int itemID
     * @param price in-data from user, the price without VAT
     * @param VATRate in-data from user, the VAT-rate
     *
     */
    public GoodsDTO(String itemDescription, int itemID, Amount price, int VATRate){
        this.itemDescription = itemDescription;
        this.itemID = itemID;
        this.price = price;
        this.VATRate = VATRate;
        VATamount = price.takePercent(VATRate);
        itemPriceInclVAT = price.plus(VATamount);
        this.existsInDatabase = false;
    }


    public String getItemDescription() {
        return itemDescription;
    }

    public Amount getPrice() {
        return price;
    }

    public Amount getItemPriceInclVAT() {
        return itemPriceInclVAT;
    }

    public int getVATRate() {
        return VATRate;
    }

    public Amount getVATAmount() {return VATamount;}

    public boolean isExistsInDatabase() {
        return existsInDatabase;
    }

    public int getItemID() {
        return itemID;
    }

    public String toString (){
        return "|ItemID: " + itemID +" | Exists in database: " + existsInDatabase +" | Price:" + price.getStringAmount() + "kr | VAT-Rate: " +
                VATRate*100 + "% | " + itemDescription +"|";
    }
}
