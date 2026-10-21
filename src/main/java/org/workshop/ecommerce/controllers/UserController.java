package org.workshop.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workshop.ecommerce.entities.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        User user = new User(1L, "Maria", "maria@gmail.com", "132432", "1334");
        return ResponseEntity.ok()
                .body(List.of(user));
    }
}
