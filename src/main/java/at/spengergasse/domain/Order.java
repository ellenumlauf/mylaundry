package at.spengergasse.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import lombok.*;


@Getter
@Setter
@ToString
//NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "orderId", callSuper = false)

@Entity
public class Order {
    @Id
    private Long      orderId;
    private LocalDate orderDate;
    private String    customerName;
    private String    laundryType;
    private Double    price;
    private Integer   numberLaundry;
    private Boolean   expressService;

    private static final AtomicLong sequence = new AtomicLong(1000);
    private static final String [] laundryTypes = {"Trousers", "Dress", "Skirt", "Jumper", "LeatherJacket"};

    public Order() {
        setOrderId();
        setOrderDate(LocalDate.now());
        setCustomerName("unknown");
        setLaundryType("Trousers");
        setPrice(15.0);
        setNumberLaundry(1);
        setExpressService(false);
    }

    public Order(LocalDate orderDate, String customerName, String laundryType, Double price, Integer numberLaundry, Boolean expressService) {
        setOrderId();
        setOrderDate(orderDate);
        setCustomerName(customerName);
        setLaundryType(laundryType);
        setPrice(price);
        setNumberLaundry(numberLaundry);
        setExpressService(expressService);
    }
    public Order(Long orderId, LocalDate orderDate, String customerName, String laundryType, Double price, Integer numberLaundry, Boolean expressService) {
        setOrderId(orderId);
        setOrderDate(orderDate);
        setCustomerName(customerName);
        setLaundryType(laundryType);
        setPrice(price);
        setNumberLaundry(numberLaundry);
        setExpressService(expressService);
    }

    public void setOrderId() {
        orderId = sequence.getAndIncrement();
    }

    public void setPrice(Double price) {
        if (price < 7)
            throw new OrderException("Price is too low!");
        if (price > 500)
            throw new OrderException("Price is too high!");
        this.price = price;
    }

    public void setLaundryTypes(String laundryType) {
        if (!Arrays.asList(laundryTypes).contains(laundryType))
            throw new OrderException("Unknown laundry type!");
        this.laundryType = laundryType;
    }

    @Override
    public Order clone() {
        return new Order(orderId, orderDate, customerName, laundryType, price, numberLaundry, expressService);
    }
}