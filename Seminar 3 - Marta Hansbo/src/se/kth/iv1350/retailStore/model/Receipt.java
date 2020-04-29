package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.retailStore.integration.Printer;
import se.kth.iv1350.retailStore.integration.SaleDTO;
import se.kth.iv1350.retailStore.integration.Amount;

import java.util.ArrayList;

/**
 * represents a receipt
 *
 */

public class Receipt {
    private final Printer printer = new Printer();
    private final StoreDTO storeDTO = new StoreDTO();
    private final String timeAndDate;
    private final ArrayList<Goods> finalGoods;
    private final Amount finalTotal;
    private final Amount finalTotalVAT;
    private final Amount finalDiscount;
    private final Amount finalTotalAfterDiscount;
    private final Amount amountPaid;
    private final Amount amountChange;
    private final Amount rounding;

    /**
     * creates and prints a Receipt
     * @param finalSale contains all data from a finalized sale
     *
     */
    public Receipt(SaleDTO finalSale) {
        timeAndDate=finalSale.getTimeAndDate();
        finalGoods = finalSale.getFinalCart().getListOfGoods();
        finalTotal = finalSale.getFinalTotal();
        finalTotalVAT = finalSale.getFinalTotalVAT();
        finalTotalAfterDiscount = finalSale.getFinalTotalAfterDiscount();
        finalDiscount = finalSale.getFinalDiscount().getDiscountAmount();
        amountPaid = finalSale.getFinalPayment().getAmountPaid();
        amountChange = finalSale.getFinalPayment().getAmountChange();
        rounding = finalSale.getFinalPayment().getRounding();
    }

    public void printReceipt(Receipt receipt){
        printer.printReceipt(receipt);
    }

    /**
     * overwrites the toString method
     * @return String a formatted string describing the receipt
     */
    public String toString() {
        StringBuilder receiptStr = new StringBuilder();
        int itemQuantity;
        String itemDescription;
        String itemPriceInclVAT;
        String goodsPriceInclVAT;
        String goodsForm = "%s %n  ...%7d x %5s %15s %n";
        receiptStr.append(timeAndDate).append(String.format("%n",""));
        for (Goods someG : finalGoods) {
            itemDescription = someG.getGoodsDescription().getItemDescription();
            itemQuantity =  someG.getNumberOfItems();
            itemPriceInclVAT = someG.getGoodsDescription().getItemPriceInclVAT().getStringAmount();
            goodsPriceInclVAT = someG.goodsPriceInclVAT().getStringAmount();
            receiptStr.append(String.format(goodsForm,itemDescription,itemQuantity,itemPriceInclVAT,goodsPriceInclVAT));
        }
        String totForm = "%-20s %15s %n";
        receiptStr.append(String.format("%s %s %s %n","Total (incl",finalTotalVAT.getStringAmount(),"VAT)"));
        receiptStr.append(String.format("  ...%31s%n",finalTotal.getStringAmount()));
        receiptStr.append(String.format(totForm,"Discount:",finalDiscount.getStringAmount()));
        receiptStr.append(String.format(totForm,"To pay:",finalTotalAfterDiscount.getStringAmount()));
        receiptStr.append(String.format(totForm,"Amount paid:",amountPaid.getStringAmount()));
        receiptStr.append(String.format(totForm,"Change:",amountChange.getStringAmount()));
        receiptStr.append(String.format(totForm,"Rounding:",rounding.getStringAmount()));
        receiptStr.append(storeDTO.toString());
        return receiptStr.toString();
    }

    /**
     *
     * @return String timeAndDate
     */
    public String getTimeAndDate() {
        return timeAndDate;
    }

    /**
     *
     * @return StoreDTO
     */
    public StoreDTO getStoreDTO() {
        return storeDTO;
    }

    /**
     *
     * @return Amount amountPaid
     */
    public Amount getAmountPaid() {
        return amountPaid;
    }


    /**
     *
     * @return Amount amountChange
     */
    public Amount getAmountChange() {
        return amountChange;
    }

    /**
     *
     * @return Amount finalTotal
     */
    public Amount getFinalTotal() {
        return finalTotal;
    }

    /**
     *
     * @return Amount finalTotalAfterDiscount
     */
    public Amount getFinalTotalAfterDiscount() {
        return finalTotalAfterDiscount;
    }

    /**
     *
     * @return Amount finalTotalVAT
     */
    public Amount getFinalTotalVAT() {
        return finalTotalVAT;
    }

    /**
     *
     * @return Amount finalDiscount
     */
    public Amount getFinalDiscount() {
        return finalDiscount;
    }

    /**
     *
     * @return Amount rounding
     */
    public Amount getRounding() {
        return rounding;
    }

    /**
     *
     * @return ArrayList finalGoods
     */
    public ArrayList<Goods> getFinalGoods() {
        return finalGoods;
    }

    /**
     *
     * @return Printer
     */
    public Printer getPrinter() {
        return printer;
    }
}