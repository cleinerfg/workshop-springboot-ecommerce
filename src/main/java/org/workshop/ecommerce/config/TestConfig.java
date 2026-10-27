package org.workshop.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.workshop.ecommerce.entities.Order;
import org.workshop.ecommerce.entities.User;
import org.workshop.ecommerce.repositories.OrderRepository;
import org.workshop.ecommerce.repositories.UserRepository;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = getUsersSeed();
        userRepository.saveAll(users);

        List<Order> orders = getOrdersSeed(users);
        orderRepository.saveAll(orders);
    }

    private static List<User> getUsersSeed() {
        User user1 = User.builder()
                .name("Ana Silva")
                .email("ana.silva@email.com")
                .phone("(11) 98765-4321")
                .password("password")
                .build();

        User user2 = User.builder()
                .name("Carlos Souza")
                .email("carlos.souza@email.com")
                .phone("(21) 91234-5678")
                .password("password")
                .build();

        User user3 = User.builder()
                .name("Mariana Oliveira")
                .email("mariana.oliveira@email.com")
                .phone("(31) 99888-7766")
                .password("password")
                .build();

        return Arrays.asList(user1, user2, user3);
    }

    private static List<Order> getOrdersSeed(List<User> users) {
        Order order1 = Order.builder()
                .creationDate(Instant.parse("2019-06-20T19:53:07Z"))
                .client(users.get(0))
                .build();

        Order order2 = Order.builder()
                .creationDate(Instant.parse("2019-07-21T03:42:10Z"))
                .client(users.get(1))
                .build();

        Order order3 = Order.builder()
                .creationDate(Instant.parse("2019-07-22T15:21:22Z"))
                .client(users.get(2))
                .build();

        return Arrays.asList(order1, order2, order3);
    }
}
