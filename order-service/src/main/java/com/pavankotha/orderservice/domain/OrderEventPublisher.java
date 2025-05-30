package com.pavankotha.orderservice.domain;

import com.pavankotha.orderservice.ApplicationProperties;
import com.pavankotha.orderservice.domain.models.OrderCancelledEvent;
import com.pavankotha.orderservice.domain.models.OrderCreatedEvent;
import com.pavankotha.orderservice.domain.models.OrderDeliveredEvent;
import com.pavankotha.orderservice.domain.models.OrderErrorEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final ApplicationProperties properties;
    private final RabbitTemplate rabbitTemplate;

    OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void publish(OrderCreatedEvent event) {
        this.send(properties.newOrdersQueue(), event);
    }

    public void publish(OrderDeliveredEvent event) {
        this.send(properties.deliveredOrdersQueue(), event);
    }

    public void publish(OrderCancelledEvent event) {
        this.send(properties.cancelledOrdersQueue(), event);
    }

    public void publish(OrderErrorEvent event) {
        this.send(properties.errorOrdersQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), routingKey, payload);
    }
}
