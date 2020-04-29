package se.kth.iv1350.retailStore.model;
import se.kth.iv1350.retailStore.integration.Amount;
import se.kth.iv1350.retailStore.integration.GoodsDTO;

/**
 * represents the current sale in a new sale, contains a cart and keeps track of running total
 */
public class SaleBuilder {
    private Cart currentCart;
    private Amount runningTotal;
    private Amount runningTotalVAT;
    private final StoreDTO store;

    /**
     * creator creates a new cart and sets runningTotal and runningTotalVAT to zero
     */
    public SaleBuilder() {
        store = new StoreDTO();
        currentCart = new Cart();
        runningTotal = new Amount(0.0);
        runningTotalVAT = new Amount(0.0);
    }

    /**
     * updates the SaleBuilder with the new item that is to be added
     * @param itemToAdd a data packed containing information about the item to be added
     * @param numberOfItems the number of items to be added, can be positive or negative
     * @return RecentlyAddedDTO, a data package needed by View to display information
     */
    public RecentlyAddedDTO updateSale(GoodsDTO itemToAdd, int numberOfItems) {
        Goods newGoods = currentCart.addGoodsToCart(itemToAdd, numberOfItems);
        updateSaleAttributes(newGoods, numberOfItems);
        return new RecentlyAddedDTO(newGoods, this, numberOfItems);
    }

    /**
     * updates the runningTotal and runningTotalVAT attributes of the current sale
     * @param itemToAdd the Goods needed for information about the price and VAT
     * @param numberOfItems the number of items to add
     */
    private void updateSaleAttributes(Goods itemToAdd, int numberOfItems) {
        Amount priceToAdd = itemToAdd.getGoodsDescription().getItemPriceInclVAT().times(numberOfItems);
        runningTotal = runningTotal.plus(priceToAdd);
        Amount VATtoAdd = itemToAdd.getGoodsDescription().getVATAmount().times(numberOfItems);
        runningTotalVAT = runningTotalVAT.plus(VATtoAdd);
    }

    /**
     * calculates the total price of the sale including discount
     * @param currentDiscount the current discount
     * @return a double containing the total price
     */
    public Amount totalAfterDiscount(Discount currentDiscount) {
        Amount discountToSubtract = currentDiscount.getDiscountAmount();
        return this.runningTotal.plus(discountToSubtract);
    }

    /**
     *
     * @return Cart currentCart
     */
    public Cart getCurrentCart() {
        return currentCart;
    }

    /**
     *
     * @return Amount runningTotalIncVAT
     */
    public Amount getRunningTotalInclVAT() {
        return runningTotal;
    }

    /**
     *
     * @return Amount runningTotalVAT
     */
    public Amount getRunningTotalVAT() {
        return runningTotalVAT;
    }

    /**
     *
     * @return StoreDTO
     */
    public StoreDTO getStore() {
        return store;
    }
}
