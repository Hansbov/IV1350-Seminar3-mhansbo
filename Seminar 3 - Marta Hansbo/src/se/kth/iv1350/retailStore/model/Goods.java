package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.Amount;

/**
 * represent a type of item and the amount of that item.
 */
public class Goods {
    private int numberOfItems;
    private final GoodsDTO goodsDescription;

    /**
     * creator, creates a Goods
     * @param itemToAdd the information of the item to be added
     * @param numberOfItems the number of items
     */
    public Goods(GoodsDTO itemToAdd, int numberOfItems) {
        this.numberOfItems = numberOfItems;
        goodsDescription = itemToAdd;
    }

    /**
     * adds an amount of this item
     * @param numberOfItems the amount of the item to be added
     */
    public void addNumberOfItems(int numberOfItems) {
        this.numberOfItems = this.numberOfItems + numberOfItems;
    }

    /**
     * subtracts an amount of this item
     * @param numberOfItems the amount of item to be subtracted
     */
    public void subNumberOfItems(int numberOfItems){
        this.numberOfItems = this.numberOfItems - numberOfItems;
    }

    /**
     * calculates the price of the goods inclusive of VAT
     */
    public Amount goodsPriceInclVAT() {
        return this.goodsDescription.getItemPriceInclVAT().times(this.numberOfItems);
    }

    /**
     *
     * @return int numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     *
     * @return GoodsDTO goodsDescription
     */
    public GoodsDTO getGoodsDescription() {
        return goodsDescription;
    }

}

