package se.kth.iv1350.retailStore.model;

/**
 * represents information about a customer
 */
public class CustomerDTO {
    private final int customerID;

    /**
     * creator, for now only sets the customerID.
     * @param customerID an identification of the customer.
     */
    public CustomerDTO (int customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return int customerID
     */
    public int getCustomerID() {
        return customerID;
    }
}
