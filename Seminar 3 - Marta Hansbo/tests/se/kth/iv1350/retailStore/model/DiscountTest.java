package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {
    Discount discount;
    CustomerDTO firstCustomer;
    CustomerDTO secondCustomer;
    CustomerDTO thirdCustomer;


    @BeforeEach
    void setUp() {
        discount = new Discount();
        firstCustomer = new CustomerDTO(1);
        secondCustomer = new CustomerDTO(2);
        thirdCustomer = new CustomerDTO(3);
    }

    @AfterEach
    void tearDown() {
        discount = null;
        firstCustomer = null;
        secondCustomer = null;
        thirdCustomer = null;
    }

    // so far this only tests for expected data that are hardcoded in Discount
    @Test
    void calculateDiscount() {
        discount.calculateDiscount(firstCustomer);
        assertEquals("-5.00", discount.getDiscountAmount().getStringAmount(),"wrong discount");
        discount.calculateDiscount(secondCustomer);
        assertEquals("-2.00", discount.getDiscountAmount().getStringAmount(),"wrong discount");
        discount.calculateDiscount(thirdCustomer);
        assertEquals("-10.00", discount.getDiscountAmount().getStringAmount(),"wrong discount");
    }
}