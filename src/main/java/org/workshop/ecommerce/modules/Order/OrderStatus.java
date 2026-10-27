package org.workshop.ecommerce.modules.Order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_FOR_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELLED(5);

    private int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) return status;
        }
        throw new IllegalArgumentException(String.format("Order status with code %d does not exist", code));
    }
}
