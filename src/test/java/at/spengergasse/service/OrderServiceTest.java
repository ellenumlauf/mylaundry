package at.spengergasse.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void testToString() {
        OrderService mylaundry = new OrderService();
        mylaundry.fillTestData();
        System.out.println(mylaundry);
    }
}