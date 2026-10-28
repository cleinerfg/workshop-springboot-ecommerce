package org.workshop.ecommerce.modules.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.workshop.ecommerce.modules.order.Order;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_payment")
@Getter
@NoArgsConstructor
public class Payment {

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    private Instant moment;

    @Builder
    public Payment(Order order, Instant moment) {
        this.order = order;
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
