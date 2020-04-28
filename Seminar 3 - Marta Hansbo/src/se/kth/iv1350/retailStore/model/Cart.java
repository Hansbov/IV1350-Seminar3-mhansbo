package se.kth.iv1350.retailStore.model;

import java.util.ArrayList;
import se.kth.iv1350.retailStore.integration.*;

/**
 * represents a cart containing a list of goods
 */
public class Cart {
    private ArrayList<Goods> listOfGoods;


    /**
     * creates a cart
     */
    public Cart() {
        listOfGoods = new ArrayList<Goods>();
    }

    /**
     * Adds Goods to cart. if already in cart, adds amount of items.
     * @param itemToAdd the GoodsDTO that contains information about the item
     * @param numberOfItems the amount of the item to be added
     * @return returns the goods that has been added
     */
    public Goods addGoodsToCart(GoodsDTO itemToAdd, int numberOfItems) {
        int index = isInCartAtIndex(itemToAdd.getItemID());

        if (index<0){
            Goods goodsToAdd = new Goods(itemToAdd, numberOfItems);
            listOfGoods.add(goodsToAdd);
            return goodsToAdd;
        }
        else {
            Goods currentGoods = listOfGoods.get(index);
            if (numberOfItems>0)
                currentGoods.addNumberOfItems(numberOfItems);
            else
                currentGoods.subNumberOfItems(Math.abs(numberOfItems));
            return currentGoods;
        }
    }

    /**
     * checks if the item already exists in this Cart
     * @param itemID the itemID of the item we look for
     * @return a boolean: exists/doesn't exist.
     */
    private int isInCartAtIndex(int itemID) {
        int index = -1;
        for(int i = 0; i < listOfGoods.size(); i++){
            if (listOfGoods.get(i).getGoodsDescription().getItemID() == itemID){
                index = i;
                return index;
            }
        }
        return index;
    }

    public ArrayList<Goods> getListOfGoods() {
        return listOfGoods;
    }


}
