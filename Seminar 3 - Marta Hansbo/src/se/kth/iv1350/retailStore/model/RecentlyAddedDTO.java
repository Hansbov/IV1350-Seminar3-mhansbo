package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.Amount;

/**
 * a data transfer object containing information about price and item.
 */
public class RecentlyAddedDTO {
    private final Amount price;
    private final int nofItemsAdded;
    private final String itemDescription;
    private final Amount runningTotal;

    /**
     * creates a data package with information needed by View, Price includes VAT
     * @param currentGoods the item that is new or has a new amount
     * @param currentSale the current SaleBuilder
     * @param numberOfItems the amount of items
     */
    public RecentlyAddedDTO(Goods currentGoods, SaleBuilder currentSale, int numberOfItems){
        itemDescription = currentGoods.getGoodsDescription().getItemDescription();
        Amount priceExVAT = currentGoods.getGoodsDescription().getPrice();
        price = currentGoods.getGoodsDescription().getItemPriceInclVAT().times(numberOfItems);
        runningTotal = currentSale.getRunningTotalInclVAT();
        nofItemsAdded = numberOfItems;
    }

    /**
     *
     * @return Amount runningTotal
     */
    public Amount getRunningTotal() {
        return runningTotal;
    }

    /**
     *
     * @return Amount price
     */
    public Amount getPrice() {
        return price;
    }

    /**
     *
     * @return int numberOfItemsAdded
     */
    public int getNumberOfItemsAdded() {
        return nofItemsAdded;
    }

    /**
     *
     * @return String itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     *
     * @return String of object
     */
    public String toString() {

        return "  | " + nofItemsAdded + "x'" + itemDescription +
                "' | Price: " + price.getStringAmount() +
                "kr | Current Total: " + runningTotal.getStringAmount() +
                "kr |";
    }
}
