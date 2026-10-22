package org.workshop.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.workshop.ecommerce.entities.User;
import org.workshop.ecommerce.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        userRepository.saveAll(getUsersSeed());
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
}
