package se.kth.iv1350.retailStore.model;
/**
 *  represents information about the store
 */
public class StoreDTO {
    String storeName;
    String street;
    String city;
    int streetNumber;
    int zipCode;

    /**
     * creates instance
     */
    public StoreDTO (){
        storeName = "The Marta Store";
        street = "Big Street";
        city = "Big City";
        streetNumber = 1;
        zipCode = 1000;

    }

    public String getStoreName() {
        return storeName;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String toString() {
        return String.format("%n%s %n%s  %d %n%d  %s  %n", storeName, street, streetNumber, zipCode, city);
    }
}
