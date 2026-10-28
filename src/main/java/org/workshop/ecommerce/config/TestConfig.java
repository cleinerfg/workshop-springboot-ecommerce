package org.workshop.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.workshop.ecommerce.modules.category.Category;
import org.workshop.ecommerce.modules.category.CategoryRepository;
import org.workshop.ecommerce.modules.order.Order;
import org.workshop.ecommerce.modules.order.OrderRepository;
import org.workshop.ecommerce.modules.order.OrderStatus;
import org.workshop.ecommerce.modules.order.orderitem.OrderItem;
import org.workshop.ecommerce.modules.order.orderitem.OrderItemRepository;
import org.workshop.ecommerce.modules.payment.Payment;
import org.workshop.ecommerce.modules.product.Product;
import org.workshop.ecommerce.modules.product.ProductRepository;
import org.workshop.ecommerce.modules.user.User;
import org.workshop.ecommerce.modules.user.UserRepository;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<User> users = getUsersSeed();
        userRepository.saveAll(users);

        List<Order> orders = getOrdersSeed(users);
        orderRepository.saveAll(orders);

        List<Category> categories = getCategoriesSeed();
        categoryRepository.saveAll(categories);

        List<Product> products = getProductsSeed();
        productRepository.saveAll(products);

        Category catElectronics = categories.get(0);
        Category catBooks = categories.get(1);
        Category catComputers = categories.get(2);

        products.get(0).getCategories().add(catElectronics); // Smartphone X
        products.get(1).getCategories().add(catBooks);       // Clean Code
        products.get(2).getCategories().add(catElectronics); // Mechanical Keyboard
        products.get(2).getCategories().add(catComputers);   // Mechanical Keyboard
        products.get(3).getCategories().add(catElectronics); // Wireless Mouse
        products.get(4).getCategories().add(catComputers);   // UltraWide Monitor

        productRepository.saveAll(products);

        List<OrderItem> orderItems = getOrderItemsSeed(orders, products);
        orderItemRepository.saveAll(orderItems);

        Order orderPayed = orders.get(2);
        Payment pay = getPaymentSeed(orderPayed);
        orderPayed.setPayment(pay);
        orderRepository.save(orderPayed);
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
                .status(OrderStatus.WAITING_FOR_PAYMENT)
                .client(users.get(0))
                .build();

        Order order2 = Order.builder()
                .creationDate(Instant.parse("2019-07-21T03:42:10Z"))
                .status(OrderStatus.DELIVERED)
                .client(users.get(1))
                .build();

        Order order3 = Order.builder()
                .creationDate(Instant.parse("2019-07-22T15:21:22Z"))
                .status(OrderStatus.PAID)
                .client(users.get(2))
                .build();

        return Arrays.asList(order1, order2, order3);
    }

    private static List<Category> getCategoriesSeed() {
        Category cat1 = Category.builder()
                .name("Electronics")
                .build();

        Category cat2 = Category.builder()
                .name("Books")
                .build();

        Category cat3 = Category.builder()
                .name("Computers")
                .build();

        return List.of(cat1, cat2, cat3);
    }

    private static List<Product> getProductsSeed() {
        Product prod1 = Product.builder()
                .name("Smartphone X")
                .description("Latest generation smartphone with high-resolution camera.")
                .price(2999.99)
                .build();

        Product prod2 = Product.builder()
                .name("Clean Code")
                .description("A Handbook of Agile Software Craftsmanship.")
                .price(149.90)
                .build();

        Product prod3 = Product.builder()
                .name("Mechanical Keyboard")
                .description("RGB backlit mechanical keyboard with tactile switches.")
                .price(350.00)
                .build();

        Product prod4 = Product.builder()
                .name("Wireless Mouse")
                .description("Ergonomic wireless mouse with adjustable DPI.")
                .price(120.50)
                .build();

        Product prod5 = Product.builder()
                .name("UltraWide Monitor 29")
                .description("Full HD UltraWide IPS monitor for enhanced productivity.")
                .price(1450.00)
                .build();

        return List.of(prod1, prod2, prod3, prod4, prod5);
    }

    private static List<OrderItem> getOrderItemsSeed(List<Order> orders, List<Product> products) {
        OrderItem oi1 = OrderItem.builder()
                .order(orders.get(0))
                .product(products.get(0))
                .quantity(2)
                .price(products.get(0).getPrice())
                .build();

        OrderItem oi2 = OrderItem.builder()
                .order(orders.get(0))
                .product(products.get(2))
                .quantity(1)
                .price(products.get(2).getPrice())
                .build();

        OrderItem oi3 = OrderItem.builder()
                .order(orders.get(1))
                .product(products.get(1))
                .quantity(1)
                .price(products.get(1).getPrice())
                .build();

        OrderItem oi4 = OrderItem.builder()
                .order(orders.get(2))
                .product(products.get(4))
                .quantity(2)
                .price(products.get(4).getPrice())
                .build();

        return Arrays.asList(oi1, oi2, oi3, oi4);
    }

    private static Payment getPaymentSeed(Order order) {
        return Payment.builder()
                .order(order)
                .moment(Instant.parse("2019-07-22T17:21:22Z"))
                .build();
    }
}
