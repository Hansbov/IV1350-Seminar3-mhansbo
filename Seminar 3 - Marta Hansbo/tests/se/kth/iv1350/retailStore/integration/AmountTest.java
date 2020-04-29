package se.kth.iv1350.retailStore.integration;

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.retailStore.integration.Amount;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Amount(){
        Amount testAmount1 = new Amount("32.548");
        Amount testAmount2 = new Amount(32.548);
        Amount testAmount3 = new Amount(-32);
        int testInt = -32;
        Amount testAmount4 = new Amount(testInt);
        assertEquals("32.55",testAmount1.getStringAmount(),
                "wrong amount created using string param");
        assertEquals("32.55",testAmount2.getStringAmount(),
                "wrong amount created using double param");
        assertEquals("-32.00",testAmount3.getStringAmount(),
                "wrong amount created using implicit int param");
        assertEquals("-32.00",testAmount4.getStringAmount(),
                "wrong amount created using explicit int param");
        Amount testAmount5 = new Amount(0.01);
        Amount testAmount6 = new Amount(0.1);
        Amount testAmount7 = new Amount(-0.01);
        Amount testAmount8 = new Amount(-0.1);
        assertEquals("0.01",testAmount5.getStringAmount(),
                "wrong string created for 0<=amount<0.1");
        assertEquals("0.10",testAmount6.getStringAmount(),
                "wrong string created for 0.1<=amount<1");
        assertEquals("-0.01",testAmount7.getStringAmount(),
                "wrong string created for -0.01 < amount< -0.1");
        assertEquals("-0.10",testAmount8.getStringAmount(),
                "wrong string created for -0.1 <= amount <0 ");
    }

    @Test
    void plus() {
        Amount testAmount1 = new Amount(25.34);
        Amount testAmount2 = new Amount(0.02);
        Amount testAmount3 = new Amount(-0.02);
        Amount expSum1 = new Amount(25.36);
        Amount expSum2 = new Amount(25.32);

        Amount newAmount1 = testAmount1.plus(testAmount2);
        assertEquals(expSum1.getStringAmount(),newAmount1.getStringAmount(),
                "wrong result in sum of positive amounts");

        Amount newAmount2 = testAmount1.plus(testAmount3);
        assertEquals(expSum2.getStringAmount(),newAmount2.getStringAmount(),
                "wrong result in sum of negative amounts");
    }
    @Test
    void minus() {
        Amount testAmount1 = new Amount(25.34);
        Amount testAmount2 = new Amount(0.02);
        Amount testAmount3 = new Amount(-0.02);
        Amount expDiff1 = new Amount(25.32);
        Amount expDiff2 = new Amount(25.36);

        Amount newAmount1 = testAmount1.minus(testAmount2);
        assertEquals(expDiff1.getStringAmount(),newAmount1.getStringAmount(),
                "wrong result in subtraction of positive amounts");

        Amount newAmount2 = testAmount1.minus(testAmount3);
        assertEquals(expDiff2.getStringAmount(),newAmount2.getStringAmount(),
                "wrong result in subraction of negative amount");
    }
    @Test
    void percentageOf() {
        Amount testAmount1 = new Amount(-25.34);
        Amount test1 = testAmount1.takePercent(10);
        assertEquals("-2.53",test1.getStringAmount(),
                "wrong result calculating percent of amount");

    }
    @Test
    void getStringAmount() {
        Amount testAmount1 = new Amount(25.34);
        assertEquals("25.34",testAmount1.getStringAmount(),
                "wrong result in getStringAmount");
    }

    @Test
    void times(){
        Amount testAmount1 = new Amount(-6.0);
        assertEquals("48.00",testAmount1.times(-8).getStringAmount(),
                "wrong result multiplying an amount with an integer");
    }

    @Test
    void roundedToCoins(){
        Amount testAmount1 = new Amount(25.34);
        assertEquals("25.00",testAmount1.roundedToCoins().getStringAmount(),
                "wrong result rounding amount to integer value");
        Amount testAmount2 = new Amount(25.56);
        assertEquals("26.00",testAmount2.roundedToCoins().getStringAmount(),
                "wrong result rounding amount to integer value");
    }
}