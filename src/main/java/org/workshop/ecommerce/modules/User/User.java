package org.workshop.ecommerce.modules.User;

import jakarta.persistence.*;
import lombok.*;
import org.workshop.ecommerce.modules.Order.Order;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@Getter @Setter
@NoArgsConstructor
public class User implements Serializable {

    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String password;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private Set<Order> orders = new LinkedHashSet<>();

    @Builder
    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
