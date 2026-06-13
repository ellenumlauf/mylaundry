package at.spengergasse.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.validation.constraints.*;
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
    @NotNull (message = "order date is required")
    @PastOrPresent(message = "order is valid only in past or present")
    private LocalDate orderDate;
    @NotBlank(message = "customer name is required")
    @Size(min = 3, max = 100, message = "Size of name has to be between 3 to 100 characters")
    private String    customerName;
    @NotNull (message = "Laundry type is required")
    @Pattern (
            regexp = "Trousers|Dress|Skirt|Jumper|Leatherjacket",
            message = "Laundry type has to be: Trousers, Dress, Skirt, Jumper, or Leatherjacket"
    )
    private String    laundryType;
    @NotNull (message = "Price is required")
    @DecimalMin(value = "7.0", message = "Minimum price is 7 euro!")
    @DecimalMax(value = "500.0", message = "Maximum price is 500 euro!")
    private Double    price;
    @NotNull (message = "Number of laundry items is required")
    @Min(value = 1, message = "At least one item of laundry has to be ordered!")
    @Max(value = 100, message = " Maximum number of laundry items that can be ordered: 100!")
    private Integer   numberLaundry;
    @NotNull (message = "Type of expressService (normal=false, express=true) is required")
    private Boolean   expressService;

    private static final AtomicLong sequence = new AtomicLong(1000);

    public Order() {
        //setOrderId();
        //setOrderDate(LocalDate.now());
        //setCustomerName("unknown");
        //setLaundryType("Trousers");
        //setPrice(15.0);
        //setNumberLaundry(1);
        //setExpressService(false);
        //er hat das alles rausgelöscht
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
        this.laundryType = laundryType;
    }

    @Override
    public Order clone() {
        return new Order(orderId, orderDate, customerName, laundryType, price, numberLaundry, expressService);
    }
}