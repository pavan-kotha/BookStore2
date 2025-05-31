package com.pavankotha.notificationservice;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
class NotificationServiceApplicationTests extends AbstractIT {

    @Test
    void contextLoads() {}
}
