package se.kth.iv1350.retailStore.integration;

/**
 * the class Amount represents amounts of money with two decimals
 * internally an amount intAmount is stored
 * as an int representing the value*100
 * and as a string with two decimals
 */
public class Amount {
    private int intAmount;
    private String stringAmount;

    /**
     * Creator of Amount from its value
     * @param amount double
     */
    public Amount(double amount) {
        this.intAmount = (int)Math.round(100.0 * amount);
        this.stringAmount=composeStringAmount(this.intAmount);
    }
    /**
     * Creator of Amount from its value represented as string
     * @param amount String
     */
    public Amount(String amount) {
        this.intAmount = (int)Math.round(100.0 * Double.valueOf(amount));
        this.stringAmount=composeStringAmount(this.intAmount);
    }
    /**
     * adds (or subtracts if the argument is negative) an amount to this amount
     * @param amount1 the amount to be added to this amount
     * @return a new Amount representing the sum
     */
    public Amount plus(Amount amount1) {
        int newIntAmount = this.intAmount + amount1.intAmount;
        return new Amount(newIntAmount);
    }
    /**
     * multiplies this amount with an integer
     * @param i the int to multiply the amount with
     * @return the multiple, a new Amount
     */
    public Amount times(int i) {
        int newIntAmount = this.intAmount*i;
        return new Amount(newIntAmount);
    }
    /**
     * subtracts an amount to this amount
     * @param amount1 the amount to be added to this amount
     * @return a new Amount representing the sum
     */
    public Amount minus(Amount amount1) {
        int newIntAmount = this.intAmount - amount1.intAmount;
        return new Amount(newIntAmount);
    }
    /**
     * calculates an percentage of this amount and represents it as an amount for VAT-calculations
     * @param percentage the percentage to be used, i.e. percentage=25 corresponds to 25%
     *                   (only integer percentages are accepted)
     */
    public Amount takePercent(int percentage) {
        int percentAmount = (int)Math.round(this.intAmount*percentage/100.);
        return new Amount(percentAmount);
    }
    /**
     * rounds this amount to integer value (suitable for cash change in SEK)
     */
    public Amount roundedToCoins() {
        return new Amount(Math.round(this.intAmount/100.0));
    }

    /**
     * creates a new Amount from its internal integer representation; used for operations below
     * @param amountx100 int: 100 times the amount of money it represents
     */
    private Amount(int amountx100) {
        this.intAmount = amountx100;
        this.stringAmount=composeStringAmount(amountx100);
    }
    public String getStringAmount() { return stringAmount; }
    /**
     * composes a string to store and print the value of an amount
     * @param intAmount is the int value of the amount times 100
     */
    private String composeStringAmount(int intAmount){
        String intString = String.valueOf(intAmount);
        int slen = intString.length();
        if (this.intAmount >= 0) {
            switch(slen) {
                case 1:
                    stringAmount = "0.0" + intString;
                    break;
                case 2:
                    stringAmount = "0." + intString;
                    break;
                default:
                    int decimals = slen - 2;
                    stringAmount = intString.substring(0, decimals) + "." + intString.substring(decimals);
            }
        }
        else {
            switch (slen) {
                case 2:
                    stringAmount = "-0.0" + intString.substring(1);
                    break;
                case 3:
                    stringAmount = "-0." + intString.substring(1);
                    break;
                default:
                    int decimals = slen - 2;
                    stringAmount = intString.substring(0, decimals) + "." + intString.substring(decimals);
            }
        }
        return stringAmount;
    }
}