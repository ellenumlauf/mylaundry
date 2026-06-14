package at.spengergasse.service;

import at.spengergasse.domain.OrderException;
import at.spengergasse.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import at.spengergasse.domain.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService (OrderRepository repository) {
        this.repository = repository;

        if(repository.count() == 0)
            fillTestData(true);
    }

    public void fillTestData(boolean all) {
        // urspruenglich nur die 30 orders.add(new usw); ) gemacht
        List<Order> testOrders = List.of(
            new Order(LocalDate.of(2025, 1, 4), "Anna Müller", "Trousers", 12.5, 2, false),
            new Order(LocalDate.of(2025, 1, 6), "Ben Schmidt", "Dress", 25.0, 1, true),
            new Order(LocalDate.of(2025, 1, 12), "Clara Weber", "Skirt", 14.0, 3, false),
            new Order(LocalDate.of(2025, 1, 15), "David Fischer", "Jumper", 18.5, 2, false),
            new Order(LocalDate.of(2025, 1, 19), "Emma Bauer", "Leatherjacket", 45.0, 1, true),

            new Order(LocalDate.of(2025, 1, 22), "Felix Wagner", "Trousers", 11.0, 4, false),
            new Order(LocalDate.of(2025, 1, 26), "Greta Hoffmann", "Dress", 30.0, 1, true),
            new Order(LocalDate.of(2025, 1, 31), "Hannah Becker", "Skirt", 13.5, 2, false),
            new Order(LocalDate.of(2025, 2, 4), "Ian Schulz", "Jumper", 17.0, 1, false),
            new Order(LocalDate.of(2025, 2, 6), "Julia Koch", "Leatherjacket", 50.0, 1, true),

            new Order(LocalDate.of(2025, 2, 7), "Kevin Richter", "Trousers", 10.0, 3, false),
            new Order(LocalDate.of(2025, 2, 11), "Laura Klein", "Dress", 28.0, 2, true),
            new Order(LocalDate.of(2025, 2, 14), "Moritz Wolf", "Skirt", 15.0, 1, false),
            new Order(LocalDate.of(2025, 2, 17), "Nina Schröder", "Jumper", 19.0, 2, false),
            new Order(LocalDate.of(2025, 2, 23), "Oskar Neumann", "Leatherjacket", 55.0, 1, true),

            new Order(LocalDate.of(2025, 2, 25), "Paula Braun", "Trousers", 12.0, 2, false),
            new Order(LocalDate.of(2025, 2, 27), "Quentin Lange", "Dress", 26.0, 1, true),
            new Order(LocalDate.of(2025, 3, 6), "Rosa Hartmann", "Skirt", 13.0, 3, false),
            new Order(LocalDate.of(2025, 3, 11), "Simon Krüger", "Jumper", 16.5, 2, false),
            new Order(LocalDate.of(2025, 3, 16), "Tina Sommer", "Leatherjacket", 60.0, 1, true),

            new Order(LocalDate.of(2025, 3, 18), "Uwe Brandt", "Trousers", 11.5, 3, false),
            new Order(LocalDate.of(2025, 3, 24), "Vera Lehmann", "Dress", 27.5, 1, true),
            new Order(LocalDate.of(2025, 3, 25), "Walter Günther", "Skirt", 14.5, 2, false),
            new Order(LocalDate.of(2025, 3, 28), "Xenia Albrecht", "Jumper", 18.0, 1, false),
            new Order(LocalDate.of(2025, 3, 30), "Yusuf Özdemir", "Leatherjacket", 52.0, 1, true),

            new Order(LocalDate.of(2025, 4, 1), "Zara Fuchs", "Trousers", 10.5, 2, false),
            new Order(LocalDate.of(2025, 4, 3), "Lukas Peters", "Dress", 29.0, 1, true),
            new Order(LocalDate.of(2025, 4, 10), "Mia König", "Skirt", 13.8, 2, false),
            new Order(LocalDate.of(2025, 4, 16), "Noah Hoff", "Jumper", 17.5, 1, false),
            new Order(LocalDate.of(2025, 4, 21), "Sophie Lange", "Leatherjacket", 58.0, 1, true)
        );

        ArrayList<Integer> usedEntries = new ArrayList<>();
        if (all) {
            repository.saveAll(testOrders);
        } else {
            for(int i = 0; i < 10; i++) {
                Random r= new Random();
                int r1 = r.nextInt(30);
                System.out.println("random: " + r1);
                //System.out.println(usedEntries);
                while(!usedEntries.contains(r1)) {
                    repository.saveAll(testOrders.subList(r1, (r1 + 1)));
                    usedEntries.add(r1);
                }
            }
        }
    }

    public ArrayList<Order> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public String toString() {
        return repository.findAll().stream()
                .map(o -> o.toString())
                .collect(Collectors.joining("\n"));
    }

    public void removeAllOrders() {
        repository.deleteAll();
    }

    public void addTenOrders() {
        // man könnte for schleife machen, er macht es hardcoded!!
        fillTestData(false);
    }

    public void addOneEuro() {
        for(Order o : repository.findAll()) {
            o.setPrice(o.getPrice() + 1);
            repository.save(o);
        }
    }

    public void removeAllExpressOrders() {
        for(Order o : repository.findAll()) {
            if(o.getExpressService() == true)
                repository.deleteById(o.getOrderId());
        }
    }

    public void addInvalidData() {
        repository.save(new Order(LocalDate.of(2025, 6, 13), "Nike Brahmer", "Dress", -3.5, 1, false));
    }

    public void removeOneOrder(Long orderId) {
        /*Order order;

        for (Order o: orders) {
            if(o.getOrderId().equals(orderId))
                order = o;
        }
        orders.remove(order);*/
        if(orderId == null)
            throw new OrderException("Order Id is null!");
        if(!repository.existsById(orderId))
            throw new OrderException("Order Id does not exist!");

        repository.deleteById(orderId);
    }

    public void addOneItem(Long orderId) {
        if(orderId == null)
            throw new OrderException("Order Id is null!");

        //orders.stream().filter() usw;
        for(Order o: repository.findAll()) {
            if(o.getOrderId().equals(orderId)) {
                o.setNumberLaundry(o.getNumberLaundry() + 1);
                repository.save(o);
            }
        }
    }

    public void addOneOrder(Order order) {
        if(order == null)
            throw new OrderException("Order is null");
        repository.save(order);
    }
}
