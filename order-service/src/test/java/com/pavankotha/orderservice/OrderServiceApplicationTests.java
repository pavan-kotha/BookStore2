package com.pavankotha.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
class OrderServiceApplicationTests extends AbstractIT {

    @Test
    void contextLoads() {}
}
