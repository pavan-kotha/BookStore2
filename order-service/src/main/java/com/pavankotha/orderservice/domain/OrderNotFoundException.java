package com.pavankotha.orderservice.domain;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrderNumber(String OrdeNumber) {
        return new OrderNotFoundException("Order with Number " + OrdeNumber + " is not found");
    }
}
