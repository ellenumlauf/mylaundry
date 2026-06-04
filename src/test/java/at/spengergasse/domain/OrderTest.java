package at.spengergasse.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testWrongPrice() {
        Order a = null;
        try {
            a = new Order(LocalDate.now(), "Max", "Trousers", 1.0, 1, false);
            System.out.println(a);
            assertEquals(1, 0);
        } catch (OrderException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 1);
        }
    }

    @Test
    void testToString() {
        Order a = null;
        try {
            a = new Order(LocalDate.now(), "Max", "Trousers", 15.0, 1, false);
            System.out.println(a);
            System.out.println(a.getOrderDate());
            System.out.println(a.getCustomerName());
            a.setCustomerName("Tom");
            System.out.println(a.getCustomerName());
        } catch (OrderException e) {
            System.out.println(e.getMessage());
        }
    }
}