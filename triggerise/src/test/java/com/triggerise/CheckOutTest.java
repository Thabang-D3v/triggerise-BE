package com.triggerise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

class CheckOutTest {


    @Test
    void testCheckout2F1() throws Exception{
        CheckOut checkOut=new CheckOut("2-for-1");
        BigDecimal price=checkOut
                .scan("MUG")
                .scan("MUG")
                .scan("MUG")
                .total();
        Assertions.assertEquals(BigDecimal.valueOf(8.0),price);
    }

    @Test
    void testCheckoutBulk()throws Exception{
        CheckOut checkOut=new CheckOut("bulk");

        BigDecimal price=checkOut

                .scan("TSHIRT")
                .scan("TSHIRT")
                .scan("TSHIRT")
                .scan("MUG")
                .scan("TSHIRT")
                .total();
        Assertions.assertEquals(BigDecimal.valueOf(62.80),price);
    }
    @Test
    void test3Products() throws Exception{
        CheckOut checkOut=new CheckOut("");
        BigDecimal price=checkOut
                .scan("MUG")
                .scan("TSHIRT")
                .scan("USBKEY")
                .total();
        Assertions.assertEquals(BigDecimal.valueOf(35.00),price);
    }

    @Test
    void TestBulk()throws Exception{
        CheckOut checkOut=new CheckOut("bulk");
        BigDecimal price=checkOut
                .scan("MUG")
                .scan("TSHIRT")
                .scan("MUG")
                .scan("MUG")
                .scan("USBKEY")
                .scan("TSHIRT")
                .scan("TSHIRT")
                .total();
        Assertions.assertEquals(BigDecimal.valueOf(62.10),price);
    }
}
