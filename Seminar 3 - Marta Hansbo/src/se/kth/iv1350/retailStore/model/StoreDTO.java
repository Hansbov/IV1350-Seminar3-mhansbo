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

    /**
     *
     * @return String storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     *
     * @return String street
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @return int streetNumber
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     *
     * @return int zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     *
     * @return String city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return String of object
     */
    public String toString() {
        return String.format("%n%s %n%s  %d %n%d  %s  %n", storeName, street, streetNumber, zipCode, city);
    }
}
