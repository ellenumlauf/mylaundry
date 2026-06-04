package at.spengergasse.service;

import org.springframework.stereotype.Service;
import at.spengergasse.domain.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private ArrayList<Order> orders;

    public OrderService () {
        orders = new ArrayList<>(1000);
    }

    public void fillTestData() {
        orders.add(new Order(LocalDate.of(2025, 1, 4), "Anna Müller", "Trousers", 12.5, 2, false));
        orders.add(new Order(LocalDate.of(2025, 1, 6), "Ben Schmidt", "Dress", 25.0, 1, true));
        orders.add(new Order(LocalDate.of(2025, 1, 12), "Clara Weber", "Skirt", 14.0, 3, false));
        orders.add(new Order(LocalDate.of(2025, 1, 15), "David Fischer", "Jumper", 18.5, 2, false));
        orders.add(new Order(LocalDate.of(2025, 1, 19), "Emma Bauer", "LeatherJacket", 45.0, 1, true));

        orders.add(new Order(LocalDate.of(2025, 1, 22), "Felix Wagner", "Trousers", 11.0, 4, false));
        orders.add(new Order(LocalDate.of(2025, 1, 26), "Greta Hoffmann", "Dress", 30.0, 1, true));
        orders.add(new Order(LocalDate.of(2025, 1, 31), "Hannah Becker", "Skirt", 13.5, 2, false));
        orders.add(new Order(LocalDate.of(2025, 2, 4), "Ian Schulz", "Jumper", 17.0, 1, false));
        orders.add(new Order(LocalDate.of(2025, 2, 6), "Julia Koch", "LeatherJacket", 50.0, 1, true));

        orders.add(new Order(LocalDate.of(2025, 2, 7), "Kevin Richter", "Trousers", 10.0, 3, false));
        orders.add(new Order(LocalDate.of(2025, 2, 11), "Laura Klein", "Dress", 28.0, 2, true));
        orders.add(new Order(LocalDate.of(2025, 2, 14), "Moritz Wolf", "Skirt", 15.0, 1, false));
        orders.add(new Order(LocalDate.of(2025, 2, 17), "Nina Schröder", "Jumper", 19.0, 2, false));
        orders.add(new Order(LocalDate.of(2025, 2, 23), "Oskar Neumann", "LeatherJacket", 55.0, 1, true));

        orders.add(new Order(LocalDate.of(2025, 2, 25), "Paula Braun", "Trousers", 12.0, 2, false));
        orders.add(new Order(LocalDate.of(2025, 2, 27), "Quentin Lange", "Dress", 26.0, 1, true));
        orders.add(new Order(LocalDate.of(2025, 3, 6), "Rosa Hartmann", "Skirt", 13.0, 3, false));
        orders.add(new Order(LocalDate.of(2025, 3, 11), "Simon Krüger", "Jumper", 16.5, 2, false));
        orders.add(new Order(LocalDate.of(2025, 3, 16), "Tina Sommer", "LeatherJacket", 60.0, 1, true));

        orders.add(new Order(LocalDate.of(2025, 3, 18), "Uwe Brandt", "Trousers", 11.5, 3, false));
        orders.add(new Order(LocalDate.of(2025, 3, 24), "Vera Lehmann", "Dress", 27.5, 1, true));
        orders.add(new Order(LocalDate.of(2025, 3, 25), "Walter Günther", "Skirt", 14.5, 2, false));
        orders.add(new Order(LocalDate.of(2025, 3, 28), "Xenia Albrecht", "Jumper", 18.0, 1, false));
        orders.add(new Order(LocalDate.of(2025, 3, 30), "Yusuf Özdemir", "LeatherJacket", 52.0, 1, true));

        orders.add(new Order(LocalDate.of(2025, 4, 1), "Zara Fuchs", "Trousers", 10.5, 2, false));
        orders.add(new Order(LocalDate.of(2025, 4, 3), "Lukas Peters", "Dress", 29.0, 1, true));
        orders.add(new Order(LocalDate.of(2025, 4, 10), "Mia König", "Skirt", 13.8, 2, false));
        orders.add(new Order(LocalDate.of(2025, 4, 16), "Noah Hoff", "Jumper", 17.5, 1, false));
        orders.add(new Order(LocalDate.of(2025, 4, 21), "Sophie Lange", "LeatherJacket", 58.0, 1, true));
    }

    @Override
    public String toString() {
        return orders.stream()
                .map(o -> o.toString())
                .collect(Collectors.joining("\n"));
    }
}
